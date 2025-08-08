package br.com.alura.ChallengeLiterAlura.principal;

import br.com.alura.ChallengeLiterAlura.domain.Autor;
import br.com.alura.ChallengeLiterAlura.domain.DadosBusca;
import br.com.alura.ChallengeLiterAlura.domain.Livro;
import br.com.alura.ChallengeLiterAlura.service.AutorService;
import br.com.alura.ChallengeLiterAlura.service.ConsumoAPI;
import br.com.alura.ChallengeLiterAlura.service.ConverteDados;
import br.com.alura.ChallengeLiterAlura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    @Autowired
    private LivroService livroService;

    @Autowired
    private AutorService autorService;

    private final String URL_BASE = "https://gutendex.com/books/?search=";

    public void exibeMenu() {

        Scanner sc = new Scanner(System.in);
        int escolha = -1;

        while (escolha != 0) {
            System.out.println("\nEscolha o número de sua opção: ");
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
                    System.out.println("Digite o nome do livro: ");
                    String nomeLivro = sc.nextLine().toLowerCase().replace(" ", "%20").trim();
                    String json = ConsumoAPI.obterDados(URL_BASE + nomeLivro);
                    DadosBusca busca = ConverteDados.obterDados(json, DadosBusca.class);
                    Livro livro;
                    if (busca != null && !busca.getLivros().isEmpty()) {
                        livro = busca.getLivros().get(0);
                        livroService.createLivro(livro);
                        livro.imprimirDados();
                    } else {
                        System.out.println("Nenhum livro encontrado");
                    }
                    break;
                case 2:
                    List<Livro> todosOsLivros = livroService.findAll();
                    System.out.println("Todos os livros do Banco de Dados \n");
                    if(!todosOsLivros.isEmpty()) {
                        todosOsLivros.forEach(Livro::imprimirDados);
                    } else {
                        System.out.println("\nNão existem Livros cadastrados no Banco de Dados\n");
                    }
                    break;
                case 3:
                    List<Autor> todosOsAutores = autorService.findAll();
                    System.out.println("Todos os Autores do Banco de Dados \n");
                    if(!todosOsAutores.isEmpty()) {
                        todosOsAutores.forEach(Autor::imprimirDados);
                    } else {
                        System.out.println("\nNão existem Autores cadastrados no Banco de Dados\n");
                    }
                    break;
                case 4:
                    System.out.println("Digite o ano: ");
                    Long ano =  sc.nextLong();
                    sc.nextLine();
                    List<Autor> todosOsAutoresVivos = autorService.autoresVivosDeterminadoAno(ano);
                    if (!todosOsAutoresVivos.isEmpty()) {
                        todosOsAutoresVivos.forEach(Autor::imprimirDados);
                    } else {
                        System.out.printf("\nNenhum Autor vivo neste ano foi encontrado.\n");
                    }
                    break;
                case 5:
                    System.out.println("Insira o idioma para realizar a busca:");
                    System.out.println("es - Espanhol");
                    System.out.println("en - Inglês");
                    System.out.println("fr - Francês");
                    System.out.println("pt - Portugues");
                    String idioma = sc.nextLine().toLowerCase().trim();
                    List<Livro> todosOsLivrosNoIdioma = livroService.findByIdioma(idioma);
                    if(!todosOsLivrosNoIdioma.isEmpty()) {
                        todosOsLivrosNoIdioma.forEach(Livro::imprimirDados);
                    } else {
                        System.out.println("\nNão existem livros neste idioma no Banco de Dados\n");
                    }
                    break;
            }

        }

        System.out.println("Encerrando a aplicação!");
    }
}
