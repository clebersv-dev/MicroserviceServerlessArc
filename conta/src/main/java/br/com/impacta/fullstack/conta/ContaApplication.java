package br.com.impacta.fullstack.conta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.impacta.fullstack.conta")
public class ContaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContaApplication.class, args);
	}
}
