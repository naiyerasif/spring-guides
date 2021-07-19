package dev.mflash.guides.spring.aop.logging.aspect;

import static org.assertj.core.api.Assertions.assertThat;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import dev.mflash.guides.spring.aop.logging.AspectAppender;
import dev.mflash.guides.spring.aop.logging.ServiceProxyProvider;
import dev.mflash.guides.spring.aop.logging.service.GreetingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

class LogEntryExitAspectTest {

  private GreetingService greetingService;
  private AspectAppender aspectAppender;

  @BeforeEach
  void setUp() {
    greetingService = ServiceProxyProvider.getServiceProxy(GreetingService.class);

    aspectAppender = new AspectAppender();
    aspectAppender.setContext(new LoggerContext());
    aspectAppender.start();

    final Logger logger = (Logger) LoggerFactory.getLogger(GreetingService.class);
    logger.addAppender(aspectAppender);
  }

  @AfterEach
  void cleanUp() {
    aspectAppender.stop();
  }

  @Test
  @DisplayName("Should fire advice with logs")
  void shouldFireAdviceWithLogs() {
    greetingService.greet("Veronica");

    assertThat(aspectAppender.events).isNotEmpty()
        .anySatisfy(event -> assertThat(event.getMessage())
            .isEqualTo("Started greet method with args: {name=Veronica}")
        )
        .anySatisfy(event -> assertThat(event.getMessage())
            .startsWith("Finished greet method in ")
            .endsWith("millis with return: Hello, Veronica!")
        );
  }

  @Test
  @DisplayName("Should not fire advice without annotation")
  void shouldNotFireAdviceWithoutAnnotation() {
    greetingService.resolveName("Veronica");

    assertThat(aspectAppender.events).isEmpty();
  }
}
