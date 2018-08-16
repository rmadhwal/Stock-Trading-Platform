package com.example.demo.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.cs.trading.Repositories.OrderRepository;

@Profile("test")
@Configuration
public class OrderServiceTestConfig  {
	    @Bean
	    @Primary
	    public OrderRepository or() {
	        return Mockito.mock(OrderRepository.class);
	 }
}

