package Objetos;

import methods.Filme;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class leituraVetor {

    public static Filme[] lerArquivoCSV(String nomeArquivo, int tamVetor) {
        Filme[] filmes = new Filme[tamVetor];

        try {
            InputStream inputStream = new FileInputStream(nomeArquivo);
            Scanner scanner = new Scanner(inputStream);

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            int index = 0;
            while (scanner.hasNextLine() && index < tamVetor) {
                String linha = scanner.nextLine();

                String[] colunas = quebrarLinha(linha);

                Filme filme = new Filme();
                if (colunas.length >= 16) {
                    filme.setId(Integer.parseInt(colunas[0].trim()));
                    filme.setTitle(colunas[1].trim());
                    filme.setVote_average(colunas[2].trim());
                    filme.setVote_count(colunas[3].trim());
                    filme.setStatus(colunas[4].trim());
                    filme.setRelease_date(colunas[5].trim());
                    filme.setRevenue(colunas[6].trim());
                    filme.setRuntime(colunas[7].trim());
                    filme.setAdult(colunas[8].trim());
                    filme.setBudget(colunas[9].trim());
                    filme.setImdb_id(colunas[10].trim());
                    filme.setOriginal_language(colunas[11].trim());
                    filme.setOriginal_title(colunas[12].trim());
                    filme.setPopularity(colunas[13].trim());
                    filme.setPoster_path(colunas[14].trim());
                    filme.setGenres(colunas[15].trim());
                }

                filmes[index] = filme;
                index++;
            }

            scanner.close();
            inputStream.close();

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter dados: " + e.getMessage());
        }

        return filmes;
    }

    private static String[] quebrarLinha(String linha) {
        return linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }

    public static void exibirListaFilmes(Filme[] filmes) {
        System.out.println("\n=== LISTA DE FILMES ===\n");

        if (filmes != null) {
            int count = 0;
            for (int i = 0; i < filmes.length && filmes[i] != null; i++) {
                count++;
                Filme f = filmes[i];
                System.out.printf("[%d] ID: %d | Título: %s | Nota: %s | Votos: %s | Status: %s | Lançamento: %s | Receita: %s | Duração: %s | Adulto: %s | Orçamento: %s | IMDB: %s | Idioma: %s | Título Original: %s | Popularidade: %s | Poster: %s | Gêneros: %s\n",
                        count, f.getId(), f.getTitle(), f.getVote_average(), f.getVote_count(), f.getStatus(),
                        f.getRelease_date(), f.getRevenue(), f.getRuntime(), f.getAdult(), f.getBudget(),
                        f.getImdb_id(), f.getOriginal_language(), f.getOriginal_title(), f.getPopularity(),
                        f.getPoster_path(), f.getGenres());
            }
            System.out.println("\nTotal de filmes: " + count);
        } else {
            System.out.println("Lista de filmes vazia!");
        }
    }
}