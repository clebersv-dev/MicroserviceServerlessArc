package br.com.impacta.fullstack.debito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.impacta.fullstack.debit")
public class DebitApplication {

	public static void main(String[] args) {
		SpringApplication.run(DebitApplication.class, args);
	}

}
