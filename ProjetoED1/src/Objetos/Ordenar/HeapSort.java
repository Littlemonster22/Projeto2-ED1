package Objetos.Ordenar;

import methods.Filme;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HeapSort extends ModeloOrdernar {

    private static HeapSort instance = new HeapSort();

    public static void heapSort(Filme[] filmes) {
        instance.ordenar(filmes);
    }

    public static HeapSort getInstance() {
        return instance;
    }

    public void ordenar(Filme[] filmes) {
        if (filmes == null || filmes.length == 0) {
            this.tamanhoDoConjuntoDeDados = 0;
            this.tempoDeOrdenacao = 0;
            return;
        }

        long startTime = System.currentTimeMillis();
        this.tamanhoDoConjuntoDeDados = filmes.length;

        int n = filmes.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(filmes, n, i);
        }

        int totalTrocas = 0;
        for (int i = n - 1; i > 0; i--) {
            if (filmes[0] == null || filmes[i] == null) continue;

            Filme temp = filmes[0];
            filmes[0] = filmes[i];
            filmes[i] = temp;
            totalTrocas++;

            heapify(filmes, i, 0);
        }

        long endTime = System.currentTimeMillis();
        this.tempoDeOrdenacao = (int)(endTime - startTime);

        System.out.println("\n=== LISTA DE FILMES ORDENADA POR ID (HeapSort) ===\n");
        int count = 0;
        for (Filme f : filmes) {
            if (f != null) {
                count++;
                System.out.printf("[%d] ID: %d | Título: %s\n",
                        count, f.getId(), f.getTitle());
            }
        }
        System.out.println("\nTotal de filmes: " + count);

        salvarEstatisticas("HeapSort", n, this.tempoDeOrdenacao, totalTrocas);
    }

    private void heapify(Filme[] filmes, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && filmes[left] != null && filmes[largest] != null &&
                filmes[left].getId() > filmes[largest].getId()) {
            largest = left;
        }

        if (right < n && filmes[right] != null && filmes[largest] != null &&
                filmes[right].getId() > filmes[largest].getId()) {
            largest = right;
        }

        if (largest != i && filmes[i] != null && filmes[largest] != null) {
            Filme temp = filmes[i];
            filmes[i] = filmes[largest];
            filmes[largest] = temp;

            heapify(filmes, n, largest);
        }
    }

    private static void salvarEstatisticas(String nomeAlgoritmo, int tamanho, long tempo, int swaps) {
        try {
            File dir = new File("src/estatisticas/ORDENACAO");
            if (!dir.exists()) dir.mkdirs();

            String caminhoArquivo = "src/estatisticas/ORDENACAO/" + nomeAlgoritmo + ".txt";
            FileWriter fw = new FileWriter(caminhoArquivo, true);
            fw.write("Nome do algoritmo: " + nomeAlgoritmo + "\n");
            fw.write("Tamanho do conjunto de dados: " + tamanho + "\n");
            fw.write("Tempo de ordenacao (ms): " + tempo + "\n");
            fw.write("Numero de swaps: " + swaps + "\n\n");
            fw.close();

            System.out.println("Estatísticas salvas em: " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar estatísticas: " + e.getMessage());
        }
    }
}
