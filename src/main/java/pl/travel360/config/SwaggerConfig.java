package pl.travel360.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


}

//    @Bean
//    public Docket swaggerApiConfig() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .useDefaultResponseMessages(false)
//                .groupName("Countries API")
//                .apiInfo(apiInfo())
//                .securitySchemes(schemeList())
//                .securityContexts(securityContexts());
//    }

//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Countries API")
//                .description("API for basic info on countries")
//                .build();
//    }
//
//    private List<SecurityScheme> schemeList() {
//        List<SecurityScheme> schemeList = new ArrayList<>();
//        schemeList.add(new BasicAuth("basicAuth"));
//        return schemeList;
//    }
//
//    private SecurityReference basicAuthReference() {
//        return new SecurityReference("basicAuth", new AuthorizationScope[0]);
//    }
//
//    private List<SecurityContext> securityContexts() {
//        List<SecurityContext> securityContexts = new ArrayList<>();
//        securityContexts.add(SecurityContext.builder()
//                .securityReferences(Collections.singletonList(basicAuthReference()))
//                .forPaths(PathSelectors.any())
//                .build());
//        return securityContexts;
//    }
//}
