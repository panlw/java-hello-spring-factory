package com.el.lesson.day1;

import com.el.lesson.day1.spi.HelloSpi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.ServiceLoader;
import java.util.function.Consumer;

/**
 * @author neo.pan
 * @since 2018/11/10
 */
public class AppRunner implements ApplicationRunner {

    private final HelloSpi helloSpi;

    AppRunner(HelloSpi helloSpi) {
        this.helloSpi = helloSpi;
    }

    private void helloSpringBean() {
        printHello("SpringBean").accept(helloSpi);
    }

    private static void helloServiceLoader() {
        ServiceLoader.load(HelloSpi.class).forEach(printHello("ServiceLoader"));
    }

    @Autowired
    private ServiceLoader<HelloSpi> helloSpis;

    private void helloServiceLoaderBean() {
        helloSpis.forEach(printHello("ServiceLoaderBean"));
    }

    private static void helloSpringFactoriesLoader() {
        SpringFactoriesLoader.loadFactories(HelloSpi.class, null)
                .forEach(printHello("SpringFactoriesLoader"));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        printLine();
        helloSpringBean();

        printLine();
        helloServiceLoader();

        printLine();
        helloServiceLoaderBean();

        printLine();
        helloSpringFactoriesLoader();

        printLine();
    }

    private void printLine() {
        System.out.println("------------------------");
    }

    private static Consumer<HelloSpi> printHello(String name) {
        return helloSpi -> System.out.println(helloSpi.sayHello(name));
    }

}
