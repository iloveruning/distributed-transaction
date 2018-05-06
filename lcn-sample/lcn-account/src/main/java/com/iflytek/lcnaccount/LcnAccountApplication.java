package com.iflytek.lcnaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class LcnAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(LcnAccountApplication.class, args);
    }
}
