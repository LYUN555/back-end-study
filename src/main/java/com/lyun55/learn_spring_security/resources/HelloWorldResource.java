package com.lyun55.learn_spring_security.resources;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {

	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World v1";
	}
}

