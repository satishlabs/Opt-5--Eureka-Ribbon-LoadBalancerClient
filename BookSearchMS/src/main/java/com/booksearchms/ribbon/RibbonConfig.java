package com.booksearchms.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;

@Configuration
public class RibbonConfig {

	@Autowired
	IClientConfig iClientConfig; 


	public RibbonConfig() {
		System.out.println("RibbonConfig - D.C");
	}

	@Bean
	public IPing ribbonPing(IClientConfig iClientConfig) {
		System.out.println("MyRibbonConfig - ribbonPing()");
		return new PingUrl();
	}
	@Bean
	public IRule ribbonRule(IClientConfig iClientConfig) {
		System.out.println("MyRibbonConfig - ribbonRule()");
		//return new WeightedResponseTimeRule();
		//return new RoundRobinRule();
		return new BestAvailableRule();
	} 
}
