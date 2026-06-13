package Objetos.Ordenar;

import methods.Filme;
import java.io.FileWriter;
import java.io.IOException;

public class MergeSort extends ModeloOrdernar {

    private static int totalSwaps;

    public static void ordenar(Filme[] filmes) {
        if (filmes == null || filmes.length == 0) return;
        totalSwaps = 0;
        Filme[] workspace = new Filme[filmes.length];
        long startTime = System.currentTimeMillis();

        recMergeSort(filmes, workspace, 0, filmes.length - 1);

        long endTime = System.currentTimeMillis();
        long duracao = endTime - startTime;

        salvarEstatisticas(filmes.length, duracao, totalSwaps, "MergeSort");
    }

    private static void recMergeSort(Filme[] arr, Filme[] workspace, int lowerBound, int upperBound) {
        if (lowerBound == upperBound) return;
        int mid = (lowerBound + upperBound) / 2;
        recMergeSort(arr, workspace, lowerBound, mid);
        recMergeSort(arr, workspace, mid + 1, upperBound);
        merge(arr, workspace, lowerBound, mid + 1, upperBound);
    }

    private static void merge(Filme[] arr, Filme[] workspace, int lowPtr, int highPtr, int upperBound) {
        int j = 0;
        int lowerBound = lowPtr;
        int mid = highPtr - 1;
        int n = upperBound - lowerBound + 1;

        while (lowPtr <= mid && highPtr <= upperBound) {
            if (arr[lowPtr] == null) {
                workspace[j++] = arr[highPtr++];
                totalSwaps++;
            } else if (arr[highPtr] == null) {
                workspace[j++] = arr[lowPtr++];
                totalSwaps++;
            } else if (arr[lowPtr].getId() <= arr[highPtr].getId()) {
                workspace[j++] = arr[lowPtr++];
                totalSwaps++;
            } else {
                workspace[j++] = arr[highPtr++];
                totalSwaps++;
            }
        }

        while (lowPtr <= mid) {
            workspace[j++] = arr[lowPtr++];
            totalSwaps++;
        }
        while (highPtr <= upperBound) {
            workspace[j++] = arr[highPtr++];
            totalSwaps++;
        }

        for (int i = 0; i < n; i++) {
            arr[lowerBound + i] = workspace[i];
        }
    }

    public static void ordenarEExibir(Filme[] filmes) {
        ordenar(filmes);

        System.out.println("\n=== LISTA DE FILMES ORDENADA POR ID ===\n");
        int count = 0;
        for (Filme f : filmes) {
            if (f != null) {
                count++;
                System.out.printf("[%d] ID: %d | Título: %s\n",
                        count, f.getId(), f.getTitle());
            }
        }
        System.out.println("\nTotal de filmes: " + count);
    }

    private static void salvarEstatisticas(int tamanho, long tempo, int swaps, String nomeAlgoritmo) {
        try {
            FileWriter writer = new FileWriter("src/estatisticas/ORDENACAO/" + nomeAlgoritmo + ".txt", false);
            writer.write("Nome do algoritmo: " + nomeAlgoritmo + "\n");
            writer.write("Tamanho do conjunto de dados: " + tamanho + "\n");
            writer.write("Tempo de ordenacao (ms): " + tempo + "\n");
            writer.write("Numero de swaps: " + swaps + "\n");
            writer.close();
            System.out.println("Estatísticas salvas em src/estatisticas/ORDENACAO/" + nomeAlgoritmo + ".txt");
        } catch (IOException e) {
            System.err.println("Erro ao salvar estatísticas: " + e.getMessage());
        }
    }
}
