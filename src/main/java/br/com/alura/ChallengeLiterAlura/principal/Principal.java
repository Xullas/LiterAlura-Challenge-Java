package br.com.alura.ChallengeLiterAlura.principal;

import br.com.alura.ChallengeLiterAlura.domain.DadosBusca;
import br.com.alura.ChallengeLiterAlura.domain.Livro;
import br.com.alura.ChallengeLiterAlura.service.ConsumoAPI;
import br.com.alura.ChallengeLiterAlura.service.ConverteDados;
import br.com.alura.ChallengeLiterAlura.service.LivroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.PostConstruct;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {

    private final ConfigurableApplicationContext context;

    private final String URL_BASE = "https://gutendex.com/books/?search=";

    public Principal (ConfigurableApplicationContext context) {
        this.context = context;
    }

    //https://gutendex.com/books/?search=Dom%20Casmurro
    @PostConstruct
    public void exibeMenu() throws JsonProcessingException {

        LivroService livroService = context.getBean(LivroService.class);

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
                    System.out.println("Digite o nome do livro: ");
                    String nomeLivro = sc.nextLine().toLowerCase().replace(" ", "%20").trim();
                    String json = ConsumoAPI.obterDados(URL_BASE + nomeLivro);
                    DadosBusca busca = ConverteDados.obterDados(json, DadosBusca.class);
                    Livro livro = new Livro();
                    if (busca != null && !busca.getLivros().isEmpty()) {
                        livro = busca.getLivros().get(0);
                    }
                    livroService.createLivro(livro);
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
