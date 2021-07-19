package dev.mflash.guides.spring.aop.logging;

import dev.mflash.guides.spring.aop.logging.service.GreetingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Launcher implements CommandLineRunner {

  private final GreetingService greetingService;

  public Launcher(GreetingService greetingService) {
    this.greetingService = greetingService;
  }

  public static void main(String... args) {
    SpringApplication.run(Launcher.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    greetingService.greet("Joe");
    greetingService.greet("Jane");
  }
}
