package br.com.lucas.whatsapbot.FrameworksConfig;
import java.util.Arrays;
import java.util.HashSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private Contact contact() {
        return new Contact(
                "Lucas Lopes",
                "http://www.seusite.com.br",
                "lucaslopesllima@gmail.com");
    }
    private ApiInfoBuilder informationApi() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("Title - Rest API");
        apiInfoBuilder.description("Api para gestão de presença escolar");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.termsOfServiceUrl("Termo de uso: Open Source");
        apiInfoBuilder.license("Licença - Sua Empresa");
        apiInfoBuilder.licenseUrl("http://www.seusite.com.br");
        apiInfoBuilder.contact(this.contact());

        return apiInfoBuilder;

    }
    @Bean
    public Docket detailAPi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.select()
                .apis(RequestHandlerSelectors.basePackage("br.com.lucas.whatsapbot.Controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.informationApi().build())
                .consumes(new HashSet<String>(Arrays.asList("application/json")))
                .produces(new HashSet<String>(Arrays.asList("application/json")));
        return docket;
    }
}