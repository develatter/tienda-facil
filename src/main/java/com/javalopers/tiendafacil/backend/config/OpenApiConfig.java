package com.javalopers.tiendafacil.backend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
Acceso a través de: server.port=9000 -->
* http://localhost:9000/v3/api-docs
* http://localhost:9000/swagger-ui/index.html
* */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Tienda Fácil API")
                        .description("API REST para empleados de Tienda Fácil")
                        .version("v.0.1"))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación General")
                        .url("https://github.com/develatter/tienda-facil#"));
    }

}
