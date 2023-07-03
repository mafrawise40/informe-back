package br.com.informe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InformeApplication {

    public static void main(String[] args) {
        SpringApplication.run(InformeApplication.class, args);
    }

}
