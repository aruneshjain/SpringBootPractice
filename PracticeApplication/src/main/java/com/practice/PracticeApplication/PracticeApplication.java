package com.practice.PracticeApplication;

import com.practice.PracticeApplication.Entity.Login;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
		System.out.println(org.springframework.web.method.ControllerAdviceBean.class.getProtectionDomain().getCodeSource());


	}

}
