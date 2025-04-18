package com.oliinyk.yaroslav.accounts;

import com.oliinyk.yaroslav.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
//@ComponentScans({ @ComponentScan("com.oliinyk.yaroslav.accounts.controller") })
//@EnableJpaRepositories("com.oliinyk.yaroslav.accounts.repository")
//@EntityScan("com.oliinyk.yaroslav.accounts.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "Bank Accounts microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Yaroslav Oliinyk",
						email = "MyEmail@mail.com",
						url = "https://www.some-site.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.some-site.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Bank Accounts microservice REST API Documentation",
				url = "https://www.some-site.com"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
