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
    public GroupedOpenApi JawharPublicApi() {
        return GroupedOpenApi.builder().group("Jawhar | E-SHOP 🏪")
                                .pathsToMatch("/shop/**")
                                .pathsToExclude("**")
                                .build();}

    @Bean
    public GroupedOpenApi HanenPublicApi() {
        return GroupedOpenApi.builder().group("test2")
                .pathsToMatch("/**/**")
                .pathsToExclude("**")
                .build();}

    @Bean
    public GroupedOpenApi InesPublicApi() {
        return GroupedOpenApi.builder().group("Ines |")
                .pathsToMatch("/camp/**")
                .pathsToExclude("**")
                .build();}

    @Bean
    public GroupedOpenApi IslemPublicApi() {
        return GroupedOpenApi.builder().group("test")
                .pathsToMatch("/**/**")
                .pathsToExclude("**")
                .build();}

    @Bean
    public GroupedOpenApi HazemPublicApi() {
        return GroupedOpenApi.builder().group("test3")
                .pathsToMatch("/**/**")
                .pathsToExclude("**")
                .build();}


}
