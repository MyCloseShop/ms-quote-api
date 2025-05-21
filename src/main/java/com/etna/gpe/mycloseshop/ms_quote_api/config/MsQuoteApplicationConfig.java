package com.etna.gpe.mycloseshop.ms_quote_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MsQuoteApplicationConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Management API")
                        .version("2.0.0")
                        .description("API for managing users and roles"))
                .openapi("3.0.3");
    }
}