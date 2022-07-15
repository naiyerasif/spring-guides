package dev.mflash.guides.upload;

import dev.mflash.guides.upload.configuration.StorageProperties;
import dev.mflash.guides.upload.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@EnableConfigurationProperties(StorageProperties.class)
public @SpringBootApplication class Launcher implements WebFluxConfigurer {

  public static void main(String[] args) {
    SpringApplication.run(Launcher.class, args);
  }

  @Bean CommandLineRunner init(StorageService storageService) {
    return (args) -> {
      storageService.deleteAll();
      storageService.init();
    };
  }

  public @Override void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedOrigins("http://localhost:8080");
  }
}
