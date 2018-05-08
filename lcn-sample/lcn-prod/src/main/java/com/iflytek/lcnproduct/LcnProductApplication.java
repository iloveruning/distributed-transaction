package com.iflytek.lcnproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@EnableEurekaClient
@SpringBootApplication
public class LcnProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(LcnProductApplication.class, args);
    }
}
