package com.mjc.school.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.mjc.school.repository",
        "com.mjc.school.service",
        "com.mjc.school.controller",
        "com.mjc.school.main"})
public class SpringConfig {
}