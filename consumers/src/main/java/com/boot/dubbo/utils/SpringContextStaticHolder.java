package com.boot.dubbo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author zuoyun
 */
@Component
public class SpringContextStaticHolder implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    SpringContextStaticHolder.applicationContext = applicationContext;
  }

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  public static <T> T getBean(Class<T> name) {
    return applicationContext.getBean(name);
  }


}
