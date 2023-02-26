package pidev.tn.aurora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AuroraApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuroraApplication.class, args);
    }

}
