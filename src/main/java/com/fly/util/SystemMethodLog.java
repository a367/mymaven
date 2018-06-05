package com.fly.util;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SystemMethodLog {

    String describe() default "";

    boolean isWrite() default true;
}
