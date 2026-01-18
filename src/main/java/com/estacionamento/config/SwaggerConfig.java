package com.estacionamento.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
    info = @Info(title = "Estacionamento API", version = "v1")
    
    /** método de security comentado para uso futuro na authentication via jwt 
    security = @SecurityRequirement(name = "bearerAuth")
    */
)
/** Anotação inteira comentada para uso futuro junto ao authentication via jwt 
 * 
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
    */
public class SwaggerConfig {
    
}
