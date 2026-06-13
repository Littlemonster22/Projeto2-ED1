package Objetos.Ordenar;

import methods.Filme;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class BubbleSort extends ModeloOrdernar {

    public static void ordenarEExibir(Filme[] filmes) {
        if (filmes == null || filmes.length == 0) return;

        int n = filmes.length;
        boolean trocou;
        int totalTrocas = 0;

        long inicio = System.currentTimeMillis();

        for (int out = n - 1; out > 0; out--) {
            trocou = false;
            for (int in = 0; in < out; in++) {
                if (filmes[in] == null || filmes[in + 1] == null) continue;

                if (filmes[in].getId() > filmes[in + 1].getId()) {
                    Filme temp = filmes[in];
                    filmes[in] = filmes[in + 1];
                    filmes[in + 1] = temp;
                    trocou = true;
                    totalTrocas++;
                }
            }
            if (!trocou) break;
        }

        long fim = System.currentTimeMillis();
        long duracao = fim - inicio;

        System.out.println("\n=== LISTA DE FILMES ORDENADA POR ID (BubbleSort) ===\n");
        int count = 0;
        for (Filme f : filmes) {
            if (f != null) {
                count++;
                System.out.printf("[%d] ID: %d | Título: %s\n",
                        count, f.getId(), f.getTitle());
            }
        }
        System.out.println("\nTotal de filmes: " + count);

        salvarEstatisticas("BubbleSort", n, duracao, totalTrocas);
    }

    private static void salvarEstatisticas(String nomeAlgoritmo, int tamanho, long tempo, int swaps) {
        try {
            File dir = new File("src/estatisticas/ORDENACAO");
            if (!dir.exists()) dir.mkdirs();

            String caminhoArquivo = "src/estatisticas//ORDENACAO/" + nomeAlgoritmo + ".txt";
            FileWriter fw = new FileWriter(caminhoArquivo, true);
            fw.write("Nome do algoritmo: " + nomeAlgoritmo + "\n");
            fw.write("Tamanho do conjunto de dados: " + tamanho + "\n");
            fw.write("Tempo de ordenacao (ms): " + tempo + "\n");
            fw.write("Numero de swaps: " + swaps + "\n");

            fw.close();
            System.out.println("Estatísticas salvas em: " + caminhoArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar estatísticas: " + e.getMessage());
        }
    }
}
