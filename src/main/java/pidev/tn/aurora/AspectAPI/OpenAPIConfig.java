package pidev.tn.aurora.AspectAPI;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

        @Bean
        public OpenAPI springShopOpenAPI() {return new OpenAPI().info(infoAPI());}
        public Info infoAPI() {
            return new Info().title("Aurora 🏕").description("Taking it step by step, bit by bit") .contact(contactAPI());}

        public Contact contactAPI() {
            Contact contact = new Contact().name("Jawhar Contact") .email("jawher.dhaou@esprit.tn").url("https://dhaou-jawhar.github.io");
            return contact;
        }

    @Bean
    public GroupedOpenApi etudiantPublicApi() {
        return GroupedOpenApi.builder().group("Jawhar | E-SHOP 🏪")
                                .pathsToMatch("/shop/**")
                                .pathsToExclude("**")
                                .build();}
    @Bean
    public GroupedOpenApi universityPublicApi() {
        return GroupedOpenApi.builder().group("Hazem | ")
                .pathsToMatch("/**/**")
                .pathsToExclude("**")
                .build();}
    @Bean
    public GroupedOpenApi departementPublicApi() {
        return GroupedOpenApi.builder().group("Islem | Forum 🎀 ")
                .pathsToMatch("/Forum/**")
                .pathsToExclude("**")
                .build();}
    @Bean
    public GroupedOpenApi contratPublicApi() {
        return GroupedOpenApi.builder().group("Hanen | ")
                .pathsToMatch("/**/**")
                .pathsToExclude("**")
                .build();
    }
    @Bean
    public GroupedOpenApi equipePublicApi() {
        return GroupedOpenApi.builder().group("Ines | ")
                .pathsToMatch("/**/**")
                .pathsToExclude("**")
                .build();
    }
}
