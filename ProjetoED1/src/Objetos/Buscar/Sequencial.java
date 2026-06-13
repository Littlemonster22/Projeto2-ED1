package Objetos.Buscar;

import methods.Filme;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Sequencial extends ModeloBuscar {

    public static void buscaSequencial(Filme[] filmes, int id) {
        Sequencial busca = new Sequencial();
        boolean achou = busca.buscar(filmes, id);

        if (achou) {
            System.out.println("Filme encontrado!");
        } else {
            System.out.println("Filme não encontrado!");
        }

        busca.exibirEstatisticas();
        busca.salvarEstatisticas("Sequencial");
    }

    public boolean buscar(Filme[] filmes, int idProcurado) {
        long inicio = System.nanoTime();
        int comparacoes = 0;
        boolean achou = false;

        for (Filme filme : filmes) {
            if (filme == null) continue;

            comparacoes++;
            if (filme.getId() == idProcurado) {
                achou = true;
                break;
            }
        }

        long fim = System.nanoTime();

        setTempoDeExecucao((fim - inicio) / 1_000_000.0);
        setNumComparacoes(comparacoes);
        setTamanhoDoConjuntoDeDados(filmes.length);

        return achou;
    }

    public void exibirEstatisticas() {
        System.out.println("\n=== DADOS DA BUSCA SEQUENCIAL ===");
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
