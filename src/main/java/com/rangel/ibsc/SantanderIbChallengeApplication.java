package com.rangel.ibsc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.stream.IntStream;

import com.rangel.ibsc.model.Client;
import com.rangel.ibsc.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SantanderIbChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SantanderIbChallengeApplication.class, args);
	}

	//documentacao do swagger
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.rangel.ibsc.controller"))
				.build()
				.apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Internet Banking API",
				"Documentação para o desafio de fazer uma API REST de Internet Banking para o Santander",
				"1.0",
				null,
				new springfox.documentation.service.Contact("Rangel Soares Ferreira", "https://github.com/rangelsf", "rangelsofe@gmail.com"),
				null,
				null,
				Collections.emptyList()
				);
	}


	//cria clientes para fazer teste de paginacao
	@Bean
	CommandLineRunner run (ClientRepository clientRepository){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				IntStream.rangeClosed(1,40).forEach(i ->{
					Date date = null;
					try {
						date = new SimpleDateFormat("dd/MM/yyyy").parse("12/07/2021");
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Client client = new Client();
					client.setName("Rangel"+i);
					client.setExclusivePlan(true);
					client.setBirthDate(date);
					client.setBalance(i + 2000.25);
					client.setAccountNumber(i);
					clientRepository.save(client);
				});
			}
		};
	}

}
