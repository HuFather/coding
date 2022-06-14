package com.zhaohu.design.currentlimiting.extension;

import java.lang.annotation.*;

/**
 * @creator : zhaohu
 * @date : 6/14/2022
 * @description :
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Order {
    int HIGHEST = 0;
    int LOWEST = 100;

    int value() default LOWEST;
}
