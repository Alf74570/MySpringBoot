package com.campusnum.springboot;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration

public class Example {

	@RequestMapping("/hello")
	String home() {
		return "Hello World !";
	}
}
