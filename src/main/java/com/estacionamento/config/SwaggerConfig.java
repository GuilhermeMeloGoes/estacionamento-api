package com.estacionamento.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
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

    /**
     * Outra forma de configurar o Swagger e o OpenAPI
     * cria uma instancia com o a anotação bean e passa as infromações
     */

//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new io.swagger.v3.oas.models.info.Info()
//                        .title("Estacionamento API")
//                        .description("API para gestão de estacionamento.")
//                        .version("1.0")
//                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
//                        .contact(new Contact().name("Guilherme Melo").email("guilherme@gmail.com"))
//                        .contact(new Contact().name("João Vitor").email("joao@gmail.com"))
//                );
//    }

}
