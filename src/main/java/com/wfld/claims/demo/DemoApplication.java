package com.wfld.claims.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {
	"com.wfld.claims.demo.claim.controllers",
	"com.wfld.claims.demo.claim.repositories", 
	"com.wfld.claims.demo.claim.services",
	"com.wfld.claims.demo.config",
	"com.wfld.claims.demo.controllers"
})
@EntityScan(basePackages = {"com.wfld.claims.demo.claim.entities"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
