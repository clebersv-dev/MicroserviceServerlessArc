package br.com.impacta.fullstack.debito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan("br.com.impacta.fullstack.debito")
public class DebitApplication {

	public static void main(String[] args) {
		SpringApplication.run(DebitApplication.class, args);
	}

}
