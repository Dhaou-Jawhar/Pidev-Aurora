package pidev.tn.aurora.AspectAPI;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    private static final String SCHEME_NAME = "Bearer-key";
    private static final String SCHEME = "Bearer";
    private static final String FORMAT = "JWT";
    public static final String API_DESCRIPTION="In the description property, in addition to description your "+
            "overall API, your might want to provide some basic instructions to users on how to use Swagger UI. "+
            "If ther's a test account they should use, you can provide yhe information they need in this space. "+
            "In the description property, in addition to describing your "+
            "<br></br><h3> **Note**: This API requires an 'API KEY', please log into your account to access your key <a target='_blank' href=\"http://localhost:4200/api/login\">here</a></h3>.";


    @Bean
        public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(infoAPI())
                .components(new Components()
                        .addSecuritySchemes(SCHEME_NAME, createSecurityScheme()))
                .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME))
                .addSecurityItem(new SecurityRequirement().addList(FORMAT));}
    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .name(SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme(SCHEME)
                .bearerFormat(FORMAT);

    }
        public Info infoAPI() {
            return new Info().title("Aurora 🏕").description("Taking it step by step, bit by bit").description(API_DESCRIPTION).contact(contactAPI());}

        public Contact contactAPI() {
            Contact contact = new Contact().name("Jawhar Contact") .email("jawher.dhaou@esprit.tn").url("http://localhost:8083/aurora/index.html");
            return contact;
        }

    @Bean
    public GroupedOpenApi JawharPublicApi() {
        return GroupedOpenApi.builder().group("E-SHOP 🏪")
                .pathsToMatch("/wishlist/**","/order/**","/facture/**","/cart/**","/user/BestBuyerReward","/chatbot/**")
                .pathsToExclude("/product/uploadPRODUCT","/product/addP")
                .build();}

    @Bean
    public GroupedOpenApi HanenPublicApi() {
        return GroupedOpenApi.builder().group("Hanen | Event 🎆🎭")
                .pathsToMatch("/activity/**","/event/**","/WishLishEv/**","/rating/**")
                .pathsToExclude("**")
                .build();}

    @Bean
    public GroupedOpenApi InesPublicApi() {
        return GroupedOpenApi.builder().group("Ines | Camping Center 🏕")
                .pathsToMatch("/camp/**","/rev/**","/reserv/**","/campSer/**")
                .pathsToExclude("**")
                .build();}
    @Bean
    public GroupedOpenApi IslemPublicApi() {
        return GroupedOpenApi.builder().group("Islem | Forum 🎙")
                .pathsToMatch("/Forum/**")
                .pathsToExclude("**")
                .build();}
    @Bean
    public GroupedOpenApi HazemPublicApi() {
        return GroupedOpenApi.builder().group("Hazem | User-Claim 👩‍💻")
                .pathsToMatch("/user/**","/claim/**")
                .pathsToExclude("**")
                .build();}

}
