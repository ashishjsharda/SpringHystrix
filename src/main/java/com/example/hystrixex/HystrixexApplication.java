package com.example.hystrixex;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableHystrix
@RestController
public class HystrixexApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixexApplication.class, args);
	}

	@RequestMapping(value = "/")
	@HystrixCommand(fallbackMethod = "fallbackHello",commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
	})
	public String hello() throws InterruptedException {
		Thread.sleep(1000);
		return "Hello Hystrix";
	}

	public String fallbackHello(){
		return "Fallback Hello";
	}

}
