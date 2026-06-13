import methods.LeituraBusca;
import methods.LeituraOrdenar;
import methods.Filme;

import java.util.Scanner;

import static Objetos.leituraVetor.exibirListaFilmes;
import static Objetos.leituraVetor.lerArquivoCSV;


public class Main {
    public static void main(String[] args) {
        int opcao;
        Scanner sc = new Scanner(System.in);
        Filme[] filmes = null;

        System.out.println("Qual arquivo você deseja alocar?");
        System.out.println("1. Alocar Lista 100");
        System.out.println("2. Arquivo Lista 1000");
        System.out.println("3. Arquivo Lista 10000");
        System.out.println("4. Arquivo Lista 100000");
        System.out.print("5. Arquivo Lista 500000\n> ");

        try {
            opcao = sc.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Erro: Digite apenas números!");
            sc.close();
            return;
        }

        switch (opcao) {
            case 1:
                filmes = lerArquivoCSV("src/arquivos/Filmes_100L.csv", 100);
                break;
            case 2:
                filmes = lerArquivoCSV("src/arquivos/Filmes_1k.csv", 1000);
                break;
            case 3:
                filmes = lerArquivoCSV("src/arquivos/Filmes_10k.csv", 10000);
                break;
            case 4:
                filmes = lerArquivoCSV("src/arquivos/Filmes_100k.csv", 100000);
                break;
            case 5:
                filmes = lerArquivoCSV("src/arquivos/Filmes_500k.csv", 500000);
                break;
            default:
                System.out.println("Opção inválida!");
                sc.close();
                return;
        }

        if (filmes != null) {
            System.out.println("Arquivo carregado com sucesso!");

            System.out.println("Defina o método:");
            System.out.print("[1] Buscar\n[2] Ordenar");

            try {
                opcao = sc.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Erro: Digite apenas números!");
                sc.close();
                return;
            }
            exibirListaFilmes(filmes);

            switch (opcao) {
                case 1:
                    LeituraBusca.organizarArquivo(filmes);
                    break;
                case 2:
                    LeituraOrdenar.organizarArquivo(filmes);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } else {
            System.out.println("Erro ao carregar o arquivo!");
        }
    }
}