package com.facturation.facture.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI gestionDeFacturationOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestion de Facturation")
                        .description("API pour gérer les clients et les factures")
                        .version("1.0"));
    }
}
