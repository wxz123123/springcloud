package com.wxz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//@EnableZuulProxy 是一个组合注解，@EnableCircuitBreaker，@EnableDiscoveryClient,因为它有@EnableDiscoveryClient，所以可以注册到Eureka上
@EnableZuulProxy //开启网关路由功能
public class ServiceZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceZuulApplication.class, args);
	}
}
