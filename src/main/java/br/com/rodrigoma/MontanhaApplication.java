package br.com.rodrigoma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
@EnableRabbit
public class MontanhaApplication {

    // TODO 03 Annotation @EnableRabbit

    private static final Logger LOGGER = LoggerFactory.getLogger(MontanhaApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MontanhaApplication.class, args);

        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);

        LOGGER.info("========== BEANS ==========");
        for (String beanName : beanNames) {
            LOGGER.info("BEAN: {}", beanName);
        }
        LOGGER.info("========== BEANS ==========");
    }
}