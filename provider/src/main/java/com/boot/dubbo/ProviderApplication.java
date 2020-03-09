package com.boot.dubbo;

import com.boot.dubbo.repo.UserAddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EntityScan("com.boot.dubbo.model")
@RestController
@ComponentScan(basePackages = "com.boot.dubbo")
@EnableJpaRepositories(basePackages = "com.boot.dubbo.repo")
public class ProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderApplication.class, args);
	}
	@Autowired
	private UserAddressRepo userAddressRepo;

	@RequestMapping("/aaa")
	Object home() {
		return userAddressRepo.findByUserId("1");
	}

}
