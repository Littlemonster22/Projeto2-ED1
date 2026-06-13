package Objetos.Buscar;

import methods.Filme;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Binaria extends ModeloBuscar {

    public static void buscaBinaria(Filme[] filmes, int id) {
        Binaria busca = new Binaria();
        boolean achou = busca.buscar(filmes, id);

        if (achou) {
            System.out.println("Filme encontrado!");
        } else {
            System.out.println("Filme não encontrado!");
        }

        busca.exibirEstatisticas();
        busca.salvarEstatisticas("Binaria");
    }

    public boolean buscar(Filme[] filmes, int idProcurado) {
        int count = 0;
        for (Filme filme : filmes) {
            if (filme != null) count++;
        }

        Filme[] filmesValidos = new Filme[count];
        int index = 0;
        for (Filme filme : filmes) {
            if (filme != null) filmesValidos[index++] = filme;
        }

        mergeSort(filmesValidos, 0, filmesValidos.length - 1);

        long inicio = System.nanoTime();
        int comparacoes = 0;
        boolean encontrado = false;
        int esquerda = 0, direita = filmesValidos.length - 1;

        while (esquerda <= direita) {
            int meio = (esquerda + direita) / 2;
            comparacoes++;

            int idMeio = filmesValidos[meio].getId();

            if (idMeio == idProcurado) {
                encontrado = true;
                break;
            } else if (idMeio < idProcurado) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }

        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0;

        setTempoDeExecucao(tempoMs);
        setNumComparacoes(comparacoes);
        setTamanhoDoConjuntoDeDados(filmesValidos.length);

        return encontrado;
    }

    private void mergeSort(Filme[] filmes, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergeSort(filmes, esq, meio);
            mergeSort(filmes, meio + 1, dir);
            merge(filmes, esq, meio, dir);
        }
    }

    private void merge(Filme[] filmes, int esq, int meio, int dir) {
        int n1 = meio - esq + 1;
        int n2 = dir - meio;

        Filme[] esquerda = new Filme[n1];
        Filme[] direita = new Filme[n2];

        for (int i = 0; i < n1; i++) esquerda[i] = filmes[esq + i];
        for (int j = 0; j < n2; j++) direita[j] = filmes[meio + 1 + j];

        int i = 0, j = 0, k = esq;
        while (i < n1 && j < n2) {
            if (esquerda[i].getId() <= direita[j].getId()) {
                filmes[k++] = esquerda[i++];
            } else {
                filmes[k++] = direita[j++];
            }
        }

        while (i < n1) filmes[k++] = esquerda[i++];
        while (j < n2) filmes[k++] = direita[j++];
    }

    public void exibirEstatisticas() {
        System.out.println("\n=== DADOS DA BUSCA BINÁRIA ===");
        System.out.println("Tempo de execução: " + String.format("%.6f", getTempoDeExecucao()) + " ms");
        System.out.println("Número de comparações: " + getNumComparacoes());
        System.out.println("Tamanho do conjunto de dados: " + getTamanhoDoConjuntoDeDados());
        System.out.println("\n");
    }

    private void salvarEstatisticas(String tipoBusca) {
        try {
            File diretorio = new File("src/estatisticas/busca");
            if (!diretorio.exists()) diretorio.mkdirs();

            File arquivo = new File(diretorio, tipoBusca + ".txt");
            FileWriter writer = new FileWriter(arquivo);

            writer.write("Nome do tipo de busca: " + tipoBusca + "\n");
            writer.write("Tamanho do conjunto de dados: " + getTamanhoDoConjuntoDeDados() + "\n");
            writer.write("Tempo de execução (ms): " + String.format("%.6f", getTempoDeExecucao()) + "\n");
            writer.write("Número de comparações: " + getNumComparacoes() + "\n");

            writer.close();
            System.out.println("Estatísticas salvas em: " + arquivo.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
