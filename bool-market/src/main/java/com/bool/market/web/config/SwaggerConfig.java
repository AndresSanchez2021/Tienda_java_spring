package com.bool.market.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;




@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bool.market.web.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.informacoesApi().build());
    }

    private ApiInfoBuilder informacoesApi() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("Api-Crud");
        apiInfoBuilder.description("Default Crud API for Study proposes.");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.termsOfServiceUrl("Free use.");
        apiInfoBuilder.license("License - Open Source");
        apiInfoBuilder.licenseUrl("https://github.com/luansousa26");

        return apiInfoBuilder;

    }
}
