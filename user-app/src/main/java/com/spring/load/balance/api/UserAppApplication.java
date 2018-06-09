package com.spring.load.balance.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RibbonClient(name = "chatbook", configuration = ClientAppConfiguration.class)
public class UserAppApplication {
	@Autowired
	private RestTemplate template;

	@GetMapping("/call/{name}")
	public String invokeChatBookAPI(@PathVariable String name) {
		return template.getForObject("http://chatbook/getAccess/" + name, String.class);
	}

	@GetMapping("/invoke")
	public String invokeCharbook() {
		return template.getForObject("http://chatbook/chat", String.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(UserAppApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate template() {
		return new RestTemplate();
	}

	public String colorChange() {
		System.out.println("Changed color........");
		return "Changed color........";
	}
}
