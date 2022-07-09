package com.project.clean.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.project.clean"})
@EnableJpaRepositories(basePackages = "com.project.clean")
public class JPAConfiguration {

}
