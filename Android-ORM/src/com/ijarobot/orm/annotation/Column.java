package com.ijarobot.orm.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})

/**
 * @description 属性对对应的列
 * @author ijarobot (java2eer@gmail.com)
 * @date 2012-8-6 
 * @version V1.0 
 */
public @interface Column{

  public abstract String name();

  public abstract String type();

  public abstract int lenght();
}