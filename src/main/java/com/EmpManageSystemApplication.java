package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.Repo.CredentialRepo;

@SpringBootApplication
public class EmpManageSystemApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EmpManageSystemApplication.class, args);
	}

	@Autowired
	CredentialRepo cr;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EmpManageSystemApplication.class);
	}

}
