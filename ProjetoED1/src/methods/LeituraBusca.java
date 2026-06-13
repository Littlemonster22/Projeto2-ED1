package methods;

import java.util.Scanner;
import Objetos.Buscar.Sequencial;
import Objetos.Buscar.Binaria;
import Objetos.Buscar.ArvoreBinariaDeBusca;
import Objetos.Buscar.ArvoreAVL;

public class LeituraBusca {

    public static void organizarArquivo(Filme[] filmes) {
        int opcao = 0;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Selecione o metodo de busca a ser usado:");
            System.out.println("1. Sequencial");
            System.out.println("2. Binaria (Lista Pre-ordenada)");
            System.out.println("3. Arvore Binaria de Busca");
            System.out.println("4. Arvore AVL");
            System.out.println("0. Sair\n>");

            opcao = sc.nextInt();

            if (opcao == 0) {
                System.out.println("Saindo...");
                break;
            }

            System.out.print("Digite o ID do filme: ");
            int id = sc.nextInt();

            if (opcao == 2) {
                LeituraOrdenar.organizarArquivo(filmes);
            }

            switch (opcao) {
                case 1:
                    Sequencial.buscaSequencial(filmes, id);
                    break;
                case 2:
                    Binaria.buscaBinaria(filmes, id);
                    break;
                case 3:
                    ArvoreBinariaDeBusca.buscaPorBST(filmes, id);
                    break;
                case 4:
                    ArvoreAVL.buscaPorAVL(filmes, id);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

    }
}
