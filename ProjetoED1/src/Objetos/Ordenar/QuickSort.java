package Objetos.Ordenar;

import methods.Filme;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class QuickSort extends ModeloOrdernar {

    private static QuickSort instance = new QuickSort();
    private int swaps = 0;

    public static void ordenarEExibir(Filme[] filmes) {
        instance.ordenar(filmes);
        instance.exibirFilmes(filmes);
        instance.salvarEstatisticas("QuickSort");
    }

    public void ordenar(Filme[] filmes) {
        if (filmes == null || filmes.length <= 1) {
            this.tamanhoDoConjuntoDeDados = filmes != null ? filmes.length : 0;
            this.tempoDeOrdenacao = 0;
            return;
        }

        long startTime = System.currentTimeMillis();
        this.tamanhoDoConjuntoDeDados = filmes.length;

        quickSortRecursive(filmes, 0, filmes.length - 1);

        long endTime = System.currentTimeMillis();
        this.tempoDeOrdenacao = (int)(endTime - startTime);
    }

    private void quickSortRecursive(Filme[] filmes, int low, int high) {
        if (low < high) {
            int pi = partition(filmes, low, high);
            quickSortRecursive(filmes, low, pi - 1);
            quickSortRecursive(filmes, pi + 1, high);
        }
    }

    private int partition(Filme[] filmes, int low, int high) {
        while (high > low && filmes[high] == null) high--;
        if (filmes[high] == null) return low;

        int pivot = filmes[high].getId();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (filmes[j] == null) continue;

            if (filmes[j].getId() <= pivot) {
                i++;
                while (i < j && filmes[i] != null) i++;

                Filme temp = filmes[i];
                filmes[i] = filmes[j];
                filmes[j] = temp;
                swaps++;
            }
        }

        i++;
        Filme temp = filmes[i];
        filmes[i] = filmes[high];
        filmes[high] = temp;
        swaps++;

        return i;
    }

    private void exibirFilmes(Filme[] filmes) {
        System.out.println("\n=== LISTA DE FILMES ORDENADA POR ID (QuickSort) ===\n");
        int count = 0;
        for (Filme f : filmes) {
            if (f != null) {
                count++;
                System.out.printf("[%d] ID: %d | Título: %s\n", count, f.getId(), f.getTitle());
            }
        }
        System.out.println("\nTotal de filmes: " + count);
    }

    private void salvarEstatisticas(String nomeAlgoritmo) {
        try {
            File diretorio = new File("src/estatisticas/ORDENACAO");
            if (!diretorio.exists()) diretorio.mkdirs();

            File arquivo = new File(diretorio, nomeAlgoritmo + ".txt");
            FileWriter writer = new FileWriter(arquivo);

            writer.write("Nome do algoritmo: " + nomeAlgoritmo + "\n");
            writer.write("Tamanho do conjunto de dados: " + tamanhoDoConjuntoDeDados + "\n");
            writer.write("Tempo de ordenacao (ms): " + tempoDeOrdenacao + "\n");
            writer.write("Numero de swaps: " + swaps + "\n");

            writer.close();
            System.out.println("\nEstatísticas salvas em: " + arquivo.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}