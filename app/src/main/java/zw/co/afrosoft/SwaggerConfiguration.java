package zw.co.afrosoft;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setEmail("developers@afrosoft.co.zw");
        contact.setName("Afrosoft Engineering Team");
        contact.setUrl("https://www.afrosoft.co.zw/#/");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("WORK TRACK SERVICE API")
                .version("1.0")
                .contact(contact)
                .description("This is the API for the WORK TRACK SERVICE application by Tirivashe Tinarwo").termsOfService("https://www.afrosoft.co.zw/#/")
                .license(mitLicense);

        return new OpenAPI().info(info);

    }
}
