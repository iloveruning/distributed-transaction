package com.iflytek.bytetcc.sample.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

/**
 * @author llchen12
 * @date 2018/5/8
 */
@ImportResource({ "classpath:bytetcc-supports-springcloud.xml" })
@EnableFeignClients(basePackages = "com.iflytek.bytetcc.sample.consumer.client")
@SpringBootApplication
@EnableEurekaClient
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);
    }
}
