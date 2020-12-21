package com.booksearchms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.booksearchms.ribbon.RibbonConfig;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "myjlc")
public class BookSearchMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSearchMsApplication.class, args);
	}
	
}
