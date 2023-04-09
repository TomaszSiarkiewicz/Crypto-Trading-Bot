package pl.simpbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SimpBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpBotApplication.class, args);
    }

}
