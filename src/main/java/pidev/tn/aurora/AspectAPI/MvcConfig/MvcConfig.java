package pidev.tn.aurora.AspectAPI.MvcConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    public MvcConfig()
    {
        super();
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/addProduct.html");
        registry.addViewController("/index.html");

    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/**",
                        "/css/**",
                        "/resources/**",
                        "/js/**",
                        "/images/**",
                        "/api/**",
                        "/font-awesome/**"

                )
                .addResourceLocations(
                        "classpath:/static/css/",
                        "classpath:/static/js/",
                        "classpath:/static/images/",
                        "classpath:/static/api/",
                        "classpath:/resources/",
                        "classpath:/static/font-awesome/");
    }
}
