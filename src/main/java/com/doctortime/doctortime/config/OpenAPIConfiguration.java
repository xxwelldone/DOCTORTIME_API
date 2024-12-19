package com.doctortime.doctortime.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("https://doctortime-api.onrender.com");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Wesley Menezes");
        myContact.setUrl("https://github.com/xxwelldone");
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("JWT token para autenticação");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("bearerAuth");


        Info information = new Info()
                .title("Doctor Time")
                .version("1.0")
                .description("This API exposes endpoints to manage registration and management of appointments.")
                .contact(myContact);
        return new OpenAPI()
                .info(information)
                .servers(List.of(server))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", securityScheme))
                .addSecurityItem(securityRequirement);
    }
}