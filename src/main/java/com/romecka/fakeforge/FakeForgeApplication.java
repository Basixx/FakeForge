package com.romecka.fakeforge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = {MailSenderAutoConfiguration.class})
public class FakeForgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FakeForgeApplication.class, args);
    }

}
