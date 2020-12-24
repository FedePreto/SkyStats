package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DemoApplication {

	
	public static void main(String[] args) {
		//Per usare le interfacce grafiche si è dovuto utilizzare questo builder di avviamento
		 SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
		    builder.headless(false).run(args);
		
	}

}
