package com.aevans.test.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {
	
	@RequestMapping("/ping")
	@ResponseBody
	public String ping() {
		return "Hello World";
	}
	
	@RequestMapping("/exception")
	@ResponseBody
	public String exception() {
		throw new RuntimeException("It's broken");
	}

}
