package com.example.Backendtecnica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/*
    * Swagger Configuration
    *
    * http://localhost:8080/swagger-ui/
    *
*/
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetalle())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetalle() {
        return new ApiInfo("Tecnica FactorIt",
                "Libreria api rest",
                "1.0.0",
                "http://google.com",
                new Contact("Victoria", "http://google.com", "mvbozzola@gmail.com"),
                "MIT",
                "http://google.com",
                Collections.emptyList());
    }
}
