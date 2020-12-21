package com.booksearchms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient; 

import com.booksearchms.info.BookInfo;
import com.booksearchms.info.BookPriceInfo;


@RestController
public class BookSearchController {
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@GetMapping("/mybook/{bookId}")
	public BookInfo getBookById(@PathVariable Integer bookId) {
		System.out.println("---BookSearchController -- getBookById()-----");
		BookInfo bookInfo = new BookInfo(bookId, "Master Spring Boot 2", "Srinivas", "JLC", "Java");
		
		ServiceInstance instance = loadBalancerClient.choose("MyBookPriceMS");
		String baseURL = instance.getUri().toString();
		System.out.println("Base URL : "+baseURL);
		
		String endpoint = baseURL+"/bookPrice/"+bookId;
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<BookPriceInfo> restEntity = restTemplate.getForEntity(endpoint, BookPriceInfo.class);
		BookPriceInfo bookPriceInfo = restEntity.getBody();
		
		bookInfo.setPrice(bookPriceInfo.getPrice());
		bookInfo.setOffer(bookPriceInfo.getOffer());
		bookInfo.setServerPort(bookPriceInfo.getServerPort());
		System.out.println("---BookController --- getBookById()-- "+bookId+" --Port--"+bookInfo.getServerPort());
		
		return bookInfo; 
	}
}
