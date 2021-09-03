package com.service.analytics;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling //- раскоментировать для запуска методов по рассписанию
public class AnalyticsApplication {

	public static void main(String[] args){
		SpringApplication.run(AnalyticsApplication.class, args);

	}


}
