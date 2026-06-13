package Objetos.Buscar;

import methods.Filme;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ArvoreAVL extends ModeloBuscar {
    private NoAVL raiz;

    public static void buscaPorAVL(Filme[] filmes, int id) {
        ArvoreAVL busca = new ArvoreAVL();
        boolean achou = busca.buscar(filmes, id);

        if (achou) {
            System.out.println("Filme encontrado!");
        } else {
            System.out.println("Filme não encontrado!");
        }

        busca.exibirEstatisticas();
        busca.salvarEstatisticas("AVL");
    }

    public boolean buscar(Filme[] filmes, int id) {
        criarArvore(filmes);

        long inicio = System.nanoTime();
        setNumComparacoes(0);

        boolean achou = buscar(raiz, id);

        long fim = System.nanoTime();
        double tempo = (fim - inicio) / 1_000_000.0;
        setTempoDeExecucao(tempo);

        return achou;
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

    private NoAVL inserir(NoAVL noAtual, Filme novoFilme) {
        if (noAtual == null) 
            return new NoAVL(novoFilme);

        int comparacao = Integer.compare(novoFilme.getId(), noAtual.filme.getId());

        if (comparacao < 0) {
            noAtual.esq = inserir(noAtual.esq, novoFilme);
        } else if (comparacao > 0) {
            noAtual.dir = inserir(noAtual.dir, novoFilme);
        } else {
            return noAtual; 
        }

        noAtual.alt = 1 + Math.max(altura(noAtual.esq), altura(noAtual.dir));
        int fatorBalanc = fatorBalanceamento(noAtual);

        if (fatorBalanc > 1) {
            if (fatorBalanceamento(noAtual.esq) >= 0) {
                return rotacaoDireita(noAtual);
            } else {
                noAtual.esq = rotacaoEsquerda(noAtual.esq);
                return rotacaoDireita(noAtual);
            }
        }

        if (fatorBalanc < -1) {
            if (fatorBalanceamento(noAtual.dir) <= 0) {
                return rotacaoEsquerda(noAtual);
            } else {
                noAtual.dir = rotacaoDireita(noAtual.dir);
                return rotacaoEsquerda(noAtual);
            }
        }

        return noAtual;
    }

    private boolean buscar(NoAVL no, int id) {
        if (no == null) 
            return false;

        setNumComparacoes(getNumComparacoes() + 1);

        if (id == no.filme.getId()) 
            return true;
        else if (id < no.filme.getId()) 
            return buscar(no.esq, id);
        else 
            return buscar(no.dir, id);
    }

    private NoAVL rotacaoDireita(NoAVL noDesbalanceado) {
        NoAVL novoPai = noDesbalanceado.esq;
        NoAVL subArvDir = novoPai.dir;

        novoPai.dir = noDesbalanceado;
        noDesbalanceado.esq = subArvDir;

        noDesbalanceado.alt = Math.max(altura(noDesbalanceado.esq), altura(noDesbalanceado.dir)) + 1;
        novoPai.alt = Math.max(altura(novoPai.esq), altura(novoPai.dir)) + 1;

        return novoPai;
    }

    private NoAVL rotacaoEsquerda(NoAVL noDesbalanceado) {
        NoAVL novoPai = noDesbalanceado.dir;
        NoAVL subArvEsq = novoPai.esq;

        novoPai.esq = noDesbalanceado;
        noDesbalanceado.dir = subArvEsq;

        noDesbalanceado.alt = Math.max(altura(noDesbalanceado.esq), altura(noDesbalanceado.dir)) + 1;
        novoPai.alt = Math.max(altura(novoPai.esq), altura(novoPai.dir)) + 1;

        return novoPai;
    }

    private int altura(NoAVL no) {
        return (no == null) ? 0 : no.alt;
    }

    private int fatorBalanceamento(NoAVL no) {
        return (no == null) ? 0 : altura(no.esq) - altura(no.dir);
    }

    private int contarNos(NoAVL no) {
        if (no == null) 
            return 0;
        
        return 1 + contarNos(no.esq) + contarNos(no.dir);
    }

    public void exibirEstatisticas() {
        System.out.println("\n=== DADOS DA ÁRVORE AVL ===");
        System.out.println("Tempo de execução: " + String.format("%.6f", getTempoDeExecucao()) + " ms");
        System.out.println("Número de comparações: " + getNumComparacoes());
        System.out.println("Tamanho do conjunto de dados: " + getTamanhoDoConjuntoDeDados());
        System.out.println("Altura da AVL: " + getAlturaArvore());
        System.out.println("Tamanho da árvore (nós): " + getTamanhoDaArvore());
        System.out.println("\n");
    }

    private void salvarEstatisticas(String nomeBusca) {
        try {
            File diretorio = new File("src/estatisticas/BUSCA");
            if (!diretorio.exists()) diretorio.mkdirs();

            File arquivo = new File(diretorio, nomeBusca + ".txt");
            FileWriter writer = new FileWriter(arquivo);

            writer.write("Nome do tipo de busca: " + nomeBusca + "\n");
            writer.write("Tamanho do conjunto de dados: " + getTamanhoDoConjuntoDeDados() + "\n");
            writer.write("Tempo de execução (ms): " + String.format("%.6f", getTempoDeExecucao()) + "\n");
            writer.write("Número de comparações: " + getNumComparacoes() + "\n");
            writer.write("Altura da AVL: " + getAlturaArvore() + "\n");
            writer.write("Tamanho da árvore (nós): " + getTamanhoDaArvore() + "\n");

            writer.close();
            System.out.println("\nEstatísticas salvas em: " + arquivo.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
