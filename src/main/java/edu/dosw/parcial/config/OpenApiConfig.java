package edu.dosw.parcial.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI parcialOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("DOSW Parcial T2 — API REST")
                .description("API REST del parcial del segundo tercio — University Food Hub")
                .version("v1.0")
                .contact(new Contact()
                        .name("Juan Garzón")
                        .email("dosw@eci.edu.co")));
    }
}