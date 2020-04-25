package org.tutorial.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Import(ApplicationContextConfig.class)
@Profile("test")
public class TestConfig {

}