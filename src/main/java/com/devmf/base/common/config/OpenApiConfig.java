package com.devmf.base.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    private static final String BEARER_TOKEN = "Bearer Token";
    private static final String TOKEN = "JWT";
    private static final String BERARER = "bearer";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .openapi("3.0.1")
                .components(new Components())
                .info(new Info()
                        .title("API BASE")
                        .description("API base para agilizar el desarrollo de microservicios modulares")
                        .version("1.0.0"))
                .servers(List.of(new Server().url("/").description("Default Server URL")))
                .addSecurityItem(new SecurityRequirement()
                        .addList(BEARER_TOKEN)).components(new Components()
                        .addSecuritySchemes(
                                BEARER_TOKEN,
                                new SecurityScheme()
                                        .name(BEARER_TOKEN)
                                        .type(SecurityScheme.Type.HTTP)
                                        .bearerFormat(TOKEN)
                                        .in(SecurityScheme.In.HEADER)
                                        .scheme(BERARER)
                        )
                );
    }
}
