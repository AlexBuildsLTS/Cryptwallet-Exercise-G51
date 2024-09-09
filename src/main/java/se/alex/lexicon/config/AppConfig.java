package se.alex.lexicon.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "se.alex.lexicon")  // Scanning your base package
public class AppConfig {

}
