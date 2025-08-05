package br.com.alura.ChallengeLiterAlura;

import br.com.alura.ChallengeLiterAlura.Principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class ChallengeLiterAluraApplication implements CommandLineRunner {

	private final ConfigurableApplicationContext context;

	public ChallengeLiterAluraApplication(ConfigurableApplicationContext context) {
		this.context = context;
	}

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args){
		Principal principal = new Principal(context);
		principal.exibeMenu();
	}
}
