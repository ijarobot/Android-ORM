package com.ijarobot.orm.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @description  java bean类 对应的表
 * @author ijarobot (java2eer@gmail.com)
 * @date 2012-8-6 
 * @version V1.0 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface Table{
  public abstract String name();
}