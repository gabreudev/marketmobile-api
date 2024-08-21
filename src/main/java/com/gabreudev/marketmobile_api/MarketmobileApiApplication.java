package com.gabreudev.marketmobile_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger OpenApi", version = "1", description = "API desenvolvida para a versão beta do app marketmobile"))
public class MarketmobileApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketmobileApiApplication.class, args);
	}

}
