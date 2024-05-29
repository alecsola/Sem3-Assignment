package fontys.sem3.school;

import fontys.sem3.school.business.impl.StorageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class JazzClassicsApplication {
    public static void main(String[] args) {
            SpringApplication.run(JazzClassicsApplication.class, args);


    }
}