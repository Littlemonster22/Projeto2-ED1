package Objetos.Ordenar;

import methods.Filme;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SelectionSort extends ModeloOrdernar {

    private static SelectionSort instance = new SelectionSort();
    private int totalTrocas = 0;

    public static void selectionSort(Filme[] filmes) {
        instance.ordenar(filmes);
    }

    public static SelectionSort getInstance() {
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
        this.totalTrocas = 0;

        int n = filmes.length;

        for(int i = 0; i < n - 1; i++) {
            if(filmes[i] == null) continue;

            int min = i;

            for(int j = i + 1; j < n; j++) {
                if(filmes[j] == null) continue;

                if(filmes[min] == null || filmes[j].getId() < filmes[min].getId()) {
                    min = j;
                }
            }

            if(min != i && filmes[min] != null) {
                Filme temp = filmes[min];
                filmes[min] = filmes[i];
                filmes[i] = temp;
                totalTrocas++;
            }
        }

        long endTime = System.currentTimeMillis();
        long duracao = endTime - startTime;
        this.tempoDeOrdenacao = (int)duracao;

        System.out.println("\n=== LISTA DE FILMES ORDENADA POR ID (SelectionSort) ===\n");
        int count = 0;
        for (Filme f : filmes) {
            if (f != null) {
                count++;
                System.out.printf("[%d] ID: %d | Título: %s\n",
                        count, f.getId(), f.getTitle());
            }
        }
        System.out.println("\nTotal de filmes: " + count);

        salvarEstatisticas("SelectionSort", n, duracao, totalTrocas);
    }

    private void salvarEstatisticas(String nomeAlgoritmo, int tamanho, long tempo, int swaps) {
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