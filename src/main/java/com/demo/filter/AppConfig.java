package com.demo.filter;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CustomURLFilter customURLFilter = new CustomURLFilter();

        registrationBean.setFilter(customURLFilter);
        registrationBean.addUrlPatterns("/user/*");
        registrationBean.addUrlPatterns("/admin/*");
        registrationBean.setOrder(2); //set precedence
        return registrationBean;
    }
}
