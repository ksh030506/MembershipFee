package com.helpme.MembershipFee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
//API Docs 자동화 클래스
public class SwaggerConfig {
    private String version;
    private String title;

    //API 정보
    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfo(
                title,
                "Swagger로 생성한 API Docs",
                version,
                "www.membershipfee.com",
                new Contact("김상현", "", "sanghyeon030506@gmail.com"),
                "Licenses",
                "www.example.com",
                new ArrayList<>());
    }

    //V1 API
    @Bean
    public Docket apiV1() {
        version = "V1";
        title = "JupJup API " + version;

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.helpme.MembershipFee.web.controller.v1"))
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
                .apiInfo(apiInfo(title, version));
    }

}
