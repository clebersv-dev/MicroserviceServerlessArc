package br.com.impacta.fullstack.saldoextrato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan("br.com.impacta.fullstack.saldoextrato")
public class SaldoextratoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaldoextratoApplication.class, args);
	}
}
