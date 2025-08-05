package br.com.alura.ChallengeLiterAlura.principal;

import lombok.AllArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@AllArgsConstructor
public class Principal {

    private final ConfigurableApplicationContext context;

    //https://gutendex.com/books/?search=Dom%20Casmurro

    public void exibeMenu() {

        Scanner sc = new Scanner(System.in);
        int escolha = -1;

        while (escolha != 0) {
            System.out.println("Escolha o número de sua opção: ");
            System.out.println("1 - Buscar livro pelo título");
            System.out.println("2 - Listar livros registrados");
            System.out.println("3 - Listar autores registrados");
            System.out.println("4 - Listar autores vivos em um determinado ano");
            System.out.println("5 - Listar livros em um determinado idioma");
            System.out.println("0 - Sair");

            escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }

        }

        System.out.println("Encerrando a aplicação!");
        context.close();
    }
}
