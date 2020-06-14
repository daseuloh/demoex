package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class HomeController {
	
	// 로컬호스트 127.0.0.1:8080/ 로 접속
	@RequestMapping(value="/")
	public String home() {
		return "index";
	}

}
