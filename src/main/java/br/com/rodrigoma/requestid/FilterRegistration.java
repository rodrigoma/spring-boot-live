package br.com.rodrigoma.requestid;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistration {

    // TODO 02 Register Filter

    @Bean
    public FilterRegistrationBean requestIdHeaderFilter() {
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(new RequestIdHeaderFilter());
        filterRegBean.addUrlPatterns("/techtalk/*");
        filterRegBean.setName("requestIdHeaderFilter");
        filterRegBean.setOrder(1);

        return filterRegBean;
    }

    @Bean
    public FilterRegistrationBean mdcInsertingServletFilter() {
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(new MDCInsertingServletFilter());
        filterRegBean.addUrlPatterns("/v2/*", "/internal/*");
        filterRegBean.setName("mdcInsertingServletFilter");
        filterRegBean.setOrder(1);

        return filterRegBean;
    }
}