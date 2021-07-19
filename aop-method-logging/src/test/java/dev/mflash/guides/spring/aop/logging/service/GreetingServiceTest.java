package dev.mflash.guides.spring.aop.logging.service;

import static org.assertj.core.api.Assertions.assertThat;

import dev.mflash.guides.spring.aop.logging.ServiceProxyProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class GreetingServiceTest {

  private GreetingService greetingService;

  @BeforeEach
  void setUp() {
    greetingService = ServiceProxyProvider.getServiceProxy(GreetingService.class);
  }

  @ParameterizedTest
  @MethodSource
  @DisplayName("Should return greeting")
  void shouldReturnGreeting(String name, String expectedGreeting) {
    assertThat(greetingService.greet(name)).isEqualTo(expectedGreeting);
  }

  private static Stream<Arguments> shouldReturnGreeting() {
    return Stream.of(
        Arguments.of("Veronica", "Hello, Veronica!"),
        Arguments.of("", "Hello, world!")
    );
  }
}
