package com.example.OrderManagement1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * Configure the API key used for authorization.
     *
     * @return An instance of ApiKey representing the API key configuration.
     */
    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    /**
     * Configure the API information.
     *
     * @return An instance of ApiInfo containing the API information.
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Spring Boot Blog REST APIs",
                "Spring Boot Blog REST API Documentation",
                "1",
                "Terms of service",
                new Contact("Qassam Awad", "www.javaguides.net", "qassam.1999awad@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList()
        );
    }

    /**
     * Configure the Swagger docket.
     *
     * @return An instance of Docket representing the Swagger docket configuration.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey())) /*Enable authorization for APIs in Swagger UI*/
                .select()
                .apis(RequestHandlerSelectors.any())/*get all APIs in the project, you can use .basePackage to scan only APIs in specific package like (RequestHandlerSelectors.basePackage("com.edu.controller.customer"))*/
                .paths(PathSelectors.any())  // Expose all APIs, you can restrict like expose only  .paths(PathSelectors.ant("/posts/*"))
                .build();
    }

    /**
     * Configure the security context for Swagger.
     *
     * @return An instance of SecurityContext representing the security context configuration.
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    /**
     * Configure the default authorization settings.
     *
     * @return A list of SecurityReference representing the default authorization settings.
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        /*
         * For example, scope could be read, write, and specific API in OAuth2
         * */

        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}
