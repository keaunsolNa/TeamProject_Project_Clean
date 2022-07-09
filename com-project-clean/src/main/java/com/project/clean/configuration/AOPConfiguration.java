package com.project.clean.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.project.clean")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AOPConfiguration {

}
