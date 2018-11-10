package com.el.lesson.day1.spi;

/**
 * @author neo.pan
 * @since 2018/11/10
 */
public class HelloHiSpi implements HelloSpi {

    @Override
    public String sayHello(String name) {
        return "Hi, " + name;
    }

}
