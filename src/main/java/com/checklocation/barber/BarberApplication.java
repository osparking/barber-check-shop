package com.checklocation.barber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BarberApplication {

	public static void main(String[] args) {
		System.out.println("성공을 축하합니다.");
		SpringApplication.run(BarberApplication.class, args);
	}

}
