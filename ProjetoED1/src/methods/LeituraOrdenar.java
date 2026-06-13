package methods;

import java.util.Scanner;

import Objetos.Ordenar.*;

public class LeituraOrdenar {
    public static void organizarArquivo(Filme[] filmes) {
        int opcao = 0;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Selecione o método de ordenação:\n" +
                    "[1] QuickSort\n" +
                    "[2] MergeSort\n" +
                    "[3] HeapSort\n" +
                    "[4] InsertionSort\n" +
                    "[5] SelectionSort\n" +
                    "[6] BubbleSort\n> ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    QuickSort.ordenarEExibir(filmes);
                    break;
                case 2:
                    MergeSort.ordenarEExibir(filmes);
                    break;
                case 3:
                    HeapSort.heapSort(filmes);
                    break;
                case 4:
                    InsertionSort.ordenarEExibir(filmes);
                    break;
                case 5:
                    SelectionSort.selectionSort(filmes);
                    break;
                case 6:
                    BubbleSort.ordenarEExibir(filmes);
                    break;
                case 0:
                    break;
                default:
            }
            break;
        } while(opcao != 0);

    }
}