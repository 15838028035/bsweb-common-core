package com.lj.app.core.common.base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lj.app.core.common.util.SpringContextHolder;

public class SpringUtil {
  private static SpringUtil su = null;
  private ApplicationContext context;

  private SpringUtil() {
    if (context == null) {
      context = SpringContextHolder.getApplicationContext();
    }
    if (context == null) {
      context = new ClassPathXmlApplicationContext("classpath*:/spring-base.xml");
    }
  }

  synchronized public static ApplicationContext getSpringFactory() {
    if (su == null) {
      su = new SpringUtil();
    }
    return su.getContext();
  }

  public ApplicationContext getContext() {
    return context;
  }

  public void setContext(ApplicationContext context) {
    this.context = context;
  }

}
