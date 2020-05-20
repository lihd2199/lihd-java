package com.lihd.java.spring.aware;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-05-13 15:51
 **/
@Component
public class SampleForEnvironmentAware implements EnvironmentAware {

    @Override
    public void setEnvironment(Environment environment) {

        final String property = environment.getProperty("os.name");

        System.out.println(getClass().getSimpleName() + " " + property);

    }
}
