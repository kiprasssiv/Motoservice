package com.example.WS1.configuration;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
            @Bean
            public Docket api() {
                return new Docket(DocumentationType.SWAGGER_2)
                        .useDefaultResponseMessages(false)
                        .select()

                .apis(RequestHandlerSelectors.any())

                .paths(PathSelectors.any())

                .paths(Predicates.not(PathSelectors.regex("/error.*")))

                .build();
    }
}
