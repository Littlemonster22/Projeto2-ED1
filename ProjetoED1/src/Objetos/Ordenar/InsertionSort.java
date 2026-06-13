package Objetos.Ordenar;

import methods.Filme;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InsertionSort {

    public static void ordenarEExibir(Filme[] filmes) {
        if (filmes == null || filmes.length == 0) return;

        int in, out;
        int swaps = 0;

        long inicio = System.currentTimeMillis();

        for (out = 1; out < filmes.length; out++) {
            if (filmes[out] == null) break;

            Filme temp = filmes[out];
            in = out;

            while (in > 0 && filmes[in - 1] != null && filmes[in - 1].getId() > temp.getId()) {
                filmes[in] = filmes[in - 1];
                in--;
                swaps++;
            }

            filmes[in] = temp;
            if (in != out) swaps++;
        }

        long fim = System.currentTimeMillis();
        long tempo = fim - inicio;

        System.out.println("\n=== LISTA DE FILMES ORDENADA POR ID (InsertionSort) ===\n");
        int count = 0;
        for (Filme f : filmes) {
            if (f != null) {
                count++;
                System.out.printf("[%d] ID: %d | Título: %s\n",
                        count, f.getId(), f.getTitle());
            }
        }
        System.out.println("\nTotal de filmes: " + count);

        salvarEstatisticas(count, tempo, swaps, "InsertionSort");
    }

    private static void salvarEstatisticas(int tamanho, long tempo, int swaps, String nomeAlgoritmo) {
        try {
            File diretorio = new File("src/estatisticas/ORDENACAO");
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }

            File arquivo = new File(diretorio, nomeAlgoritmo + ".txt");
            FileWriter writer = new FileWriter(arquivo);

            writer.write("Nome do algoritmo: " + nomeAlgoritmo + "\n");
            writer.write("Tamanho do conjunto de dados: " + tamanho + "\n");
            writer.write("Tempo de ordenacao (ms): " + tempo + "\n");
            writer.write("Numero de swaps: " + swaps + "\n");

            writer.close();
            System.out.println("\nEstatísticas salvas em: " + arquivo.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
