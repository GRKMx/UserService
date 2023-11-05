package com.gorkemersizer.userservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.gorkemersizer.userservice.aspect.")
@EnableAspectJAutoProxy
public class AppConfig {
}
