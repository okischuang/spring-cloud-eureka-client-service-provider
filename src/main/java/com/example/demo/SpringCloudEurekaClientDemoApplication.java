package com.example.demo;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class SpringCloudEurekaClientDemoApplication {
	private final Long serverHash = System.currentTimeMillis();

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaClientDemoApplication.class, args);
	}

	
//	@HystrixCommand(fallbackMethod = "fallbackHandler")
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "AWS") String name, @RequestParam(value = "timeout") int timeout) {
		if (timeout > 0) {
			try {
				System.out.printf("timeout %d seconds", timeout);
				TimeUnit.SECONDS.sleep(timeout);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return String.format("Hello, %s, from provider %s", name, serverHash);
	}
	
//	public String fallbackHandler() {}
}
