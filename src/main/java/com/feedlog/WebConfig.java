package com.feedlog;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(

                "/img/**",
                "/styles/**")
                .addResourceLocations(
                        "classpath:/static/img/",
                        "classpath:/static/styles/");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

}