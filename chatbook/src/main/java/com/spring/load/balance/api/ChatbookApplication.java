package com.spring.load.balance.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ChatbookApplication {
	@Value("${server.port}")
	private String port;

	@GetMapping("/getAccess/{userId}")
	public String getAccessingUser(@PathVariable String userId) {
		return "current logged in user is : " + userId + " & running port : " + port;
	}
	@GetMapping("/chat")
	public String chatNow() {
		return "application is up on port : " + port;
	}
	@GetMapping("/")
	public String refresh() {
		return "";
	}

	public static void main(String[] args) {
		SpringApplication.run(ChatbookApplication.class, args);
	}
}
