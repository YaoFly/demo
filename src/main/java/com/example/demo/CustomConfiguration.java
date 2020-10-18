package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CustomConfiguration.class)
public class CustomConfiguration {
    Logger logger = LoggerFactory.getLogger(CustomConfiguration.class);
}
