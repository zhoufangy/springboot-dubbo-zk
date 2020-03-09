package com.boot.dubbo;

import com.boot.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableSwagger2
public class ConsumerApplication {


//    @Reference(version = "1.0.0")
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}

