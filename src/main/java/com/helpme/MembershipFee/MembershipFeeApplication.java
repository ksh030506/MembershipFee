package com.helpme.MembershipFee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MembershipFeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MembershipFeeApplication.class, args);
	}

}
