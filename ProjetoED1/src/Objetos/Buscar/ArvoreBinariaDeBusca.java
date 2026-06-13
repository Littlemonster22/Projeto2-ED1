package Objetos.Buscar;

import methods.Filme;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ArvoreBinariaDeBusca extends ModeloBuscar {
    private NoABB raiz;

    public static void buscaPorBST(Filme[] filmes, int id) {
        ArvoreBinariaDeBusca busca = new ArvoreBinariaDeBusca();
        boolean achou = busca.buscar(filmes, id);

        if (achou) {
            System.out.println("Filme encontrado!");
        } else {
            System.out.println("Filme não encontrado!");
        }

        busca.exibirEstatisticas();
        busca.salvarEstatisticas("BST");
    }

    public boolean buscar(Filme[] filmes, int idProcurado) {
        criarArvore(filmes);

        long inicio = System.nanoTime();
        setNumComparacoes(0);

        boolean encontrou = buscar(raiz, idProcurado);

        long fim = System.nanoTime();
        double tempo = (fim - inicio) / 1_000_000.0;
        setTempoDeExecucao(tempo);

        return encontrou;
    }

    public void criarArvore(Filme[] filmes) {
        raiz = null;
        int cont = 0;

        for (Filme filme : filmes) {
            if (filme != null) {
                inserir(filme);
                cont++;
            }
        }

        setTamanhoDoConjuntoDeDados(cont);
        setAlturaArvore(altura(raiz));
        setTamanhoDaArvore(contarNos(raiz));
    }

    private void inserir(Filme filme) {
        raiz = inserir(raiz, filme);
    }

    private NoABB inserir(NoABB noAtual, Filme filme) {
        if (noAtual == null) return new NoABB(filme);

        int comparacao = Integer.compare(filme.getId(), noAtual.filme.getId());

        if (comparacao < 0) {
            noAtual.esq = inserir(noAtual.esq, filme);
        } else if (comparacao > 0) {
            noAtual.dir = inserir(noAtual.dir, filme);
        }

        return noAtual;
    }

    private boolean buscar(NoABB no, int id) {
        if (no == null) return false;

        setNumComparacoes(getNumComparacoes() + 1);

        if (id == no.filme.getId()) return true;
        else if (id < no.filme.getId()) return buscar(no.esq, id);
        else return buscar(no.dir, id);
    }

    private int altura(NoABB no) {
        if (no == null) return -1;
        return Math.max(altura(no.esq), altura(no.dir)) + 1;
    }

    private int contarNos(NoABB no) {
        if (no == null) return 0;
        return 1 + contarNos(no.esq) + contarNos(no.dir);
    }

    public void exibirEstatisticas() {
        System.out.println("\n=== DADOS DA ÁRVORE BINÁRIA DE BUSCA ===");
        System.out.println("Tempo de execução: " + String.format("%.6f", getTempoDeExecucao()) + " ms");
        System.out.println("Número de comparações: " + getNumComparacoes());
        System.out.println("Tamanho do conjunto de dados: " + getTamanhoDoConjuntoDeDados());
        System.out.println("Altura da BST:" + getAlturaArvore());
        System.out.println("Tamanho da árvore (nós): " + getTamanhoDaArvore());
        System.out.println("\n");
    }

    private void salvarEstatisticas(String tipoBusca) {
        try {
            File diretorio = new File("src/estatisticas/BUSCA");
            if (!diretorio.exists()) diretorio.mkdirs();

            File arquivo = new File(diretorio, tipoBusca + ".txt");
            FileWriter writer = new FileWriter(arquivo);

            writer.write("Nome do tipo de busca: " + tipoBusca + "\n");
            writer.write("Tamanho do conjunto de dados: " + getTamanhoDoConjuntoDeDados() + "\n");
            writer.write("Tempo de execução (ms): " + String.format("%.6f", getTempoDeExecucao()) + "\n");
            writer.write("Número de comparações: " + getNumComparacoes() + "\n");
            writer.write("Altura da BST: " + getAlturaArvore() + "\n");
            writer.write("Tamanho da árvore (nós): " + getTamanhoDaArvore() + "\n");

            writer.close();
            System.out.println("Estatísticas salvas em: " + arquivo.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
