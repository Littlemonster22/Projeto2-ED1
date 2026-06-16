package Objetos.Buscar;

import methods.Filme;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

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

        Arrays.sort(filmesValidos);

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
