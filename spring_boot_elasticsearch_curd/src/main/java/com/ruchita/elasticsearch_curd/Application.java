package com.ruchita.elasticsearch_curd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableScheduling
@SpringBootApplication
//@EnableTransactionManagement
@ComponentScan(basePackages = {"com.ruchita.elasticsearch_curd"})
@EnableAutoConfiguration
//@EnableJpaRepositories("com.catseye.cleverTechService.dao")

public class Application implements WebMvcConfigurer  {

	public static void main(String[] args) {
	        SpringApplication.run(Application .class, args);
	    }
	
	@Bean(name = "messageSource")
    public MessageSource getMessageResource()  {
        ReloadableResourceBundleMessageSource messageResource= new ReloadableResourceBundleMessageSource();
        messageResource.setBasenames("classpath:messages/messages");
        messageResource.setDefaultEncoding("UTF-8");
        messageResource.setCacheMillis(500);
        messageResource.setUseCodeAsDefaultMessage(true);
        return messageResource;
    }

}
