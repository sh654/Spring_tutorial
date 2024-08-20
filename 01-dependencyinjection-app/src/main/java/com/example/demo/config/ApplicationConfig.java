package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.entity.Computer;
import com.example.demo.entity.Harddisk;

@Configuration
public class ApplicationConfig {
	@Bean
	public Computer getComputer() {
		return new Computer();
	}

    @Bean
    public Harddisk getHardisk() {
		return new Harddisk();
	}
}
