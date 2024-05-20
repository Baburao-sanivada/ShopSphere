package com.backend.ShopSphere.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class SpringFoxConfig {                                    
    // @Bean
    // public Docket api() { 
    //     return new Docket(DocumentationType.SWAGGER_2)  
    //       .select()                                  
    //       .apis(RequestHandlerSelectors.any())              
    //       .paths(PathSelectors.any())                          
    //       .build();                                           
    // }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30) // OpenAPI 3.0
                .select()
                .apis(RequestHandlerSelectors.any()) // Adjust package to your controllers
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("My API")
                .description("API documentation for My Application")
                .version("1.0.0")
                .build();
    }
}
