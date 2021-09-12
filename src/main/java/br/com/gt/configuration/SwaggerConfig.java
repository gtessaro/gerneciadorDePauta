package br.com.gt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
   
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.gt.controller"))
				.paths(PathSelectors.regex("/api.*"))
				.build()
				.apiInfo(apiInfo());
	}

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Aplicação de Controle de Pautas")
                .description("Projeto de Aplicação de Controle de Pautas")
                .version("0.0.1")
                .contact(new Contact("Guilherme Tessaro", "https://www.linkedin.com/in/guilherme-tessaro-70410099/", "guilherme.tessaro27@gmail.com"))
                .build();
    }
    
}
