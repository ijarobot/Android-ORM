package com.ijarobot.orm.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @description  ID，一般为主键
 * @author ijarobot (java2eer@gmail.com)
 * @date 2012-8-6 
 * @version V1.0 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface Id{
}