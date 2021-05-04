package br.com.impacta.fullstack.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

//	@Value("${spring.cloud.config.server.git.username}")
//	private String username;
	
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
