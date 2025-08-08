package br.com.alura.ChallengeLiterAlura;

import br.com.alura.ChallengeLiterAlura.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ChallengeLiterAluraApplication implements CommandLineRunner {

	@Autowired
	private Principal principal;


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ChallengeLiterAluraApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

	@Override
	public void run(String... args){
		principal.exibeMenu();
	}
}
