package com.wxz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class EurekaClientApplication {
	@Value("${server.port}")
	private String port;
	@RequestMapping("/getPost")
	public String getPost(@RequestParam String name) {
		return "Hello:"+name+"  port:"+port;
	}
	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}
}
