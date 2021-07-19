package dev.mflash.guides.spring.aop.logging;

import dev.mflash.guides.spring.aop.logging.aspect.LogEntryExitAspect;
import dev.mflash.guides.spring.aop.logging.service.GreetingService;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.framework.DefaultAopProxyFactory;

public final class ServiceProxyProvider {

  private ServiceProxyProvider() {}

  public static <T> T getServiceProxy(Class<T> clazz) {
    final var entryExitAspect = new LogEntryExitAspect();
    final var proxyFactory = new AspectJProxyFactory(new GreetingService());
    proxyFactory.addAspect(entryExitAspect);

    final AopProxy aopProxy = new DefaultAopProxyFactory().createAopProxy(proxyFactory);

    return clazz.cast(aopProxy.getProxy());
  }
}
