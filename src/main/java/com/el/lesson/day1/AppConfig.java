package com.el.lesson.day1;

import com.el.lesson.day1.spi.HelloHiSpi;
import com.el.lesson.day1.spi.HelloSpi;
import org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author neo.pan
 * @since 2018/11/10
 */
@Configuration
public class AppConfig {

    @Bean
    AppRunner appRunner(HelloSpi helloSpi) {
        return new AppRunner(helloSpi);
    }

    @Bean
    HelloSpi helloSpi() {
        return new HelloHiSpi();
    }

    @Bean
    ServiceLoaderFactoryBean helloSpiServiceLoader() {
        return new ServiceLoaderFactoryBean() {{
            setServiceType(HelloSpi.class);
        }};
    }

}
