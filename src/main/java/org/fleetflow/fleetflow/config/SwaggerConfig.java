package org.fleetflow.fleetflow.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI fleetFlowOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FleetFlow API")
                        .description("API pour la gestion des véhicules, clients et livraisons")
                        .version("1.0.0"));
    }
}