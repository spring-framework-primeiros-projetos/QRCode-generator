/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.generator.barcode.docgen;


import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Alvaro
 */
@Configuration
@EnableSwagger2
public class swaggerConfig {
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.generator.barcode.controller"))
            .paths(PathSelectors.any())
            .build()
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
            .apiInfo(apiInfo());
        
    }
    
    private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
            .title("Gerador de Códigos de Barras")
            .description("Gerador de qr code EAN13, PDF417, CODE128, QRCode. \n Seviço alocado em: https://github.com/spring-framework-primeiros-projetos/QRCode-generator")
            .version("0.1.0")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .contact(new Contact("Alvaro Pereira do Nascimento", "https://www.linkedin.com/in/%C3%A1lvaro-nascimento-a937466a/", "alvaro.nascimentoapn@gmail.com") {})
            .build();
    }
    
    private List<ResponseMessage> responseMessageForGET()
    {
        return new ArrayList<ResponseMessage>() {
            private static final long serialVersionUID = 1L;

            {
            add(new ResponseMessageBuilder()   
                .code(500)
                .message("Foi gerada uma exceção")
                .responseModel(new ModelRef("Error"))
                .build());
            add(new ResponseMessageBuilder() 
                .code(403)
                .message("Você não tem permissão para acessar este recurso")
                .build());
        }};
    }
    
}
