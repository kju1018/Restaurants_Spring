package kr.co.fastcampus.eatgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EatgoCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EatgoCustomerApplication.class, args);
	}

}
//http localhost:8080/restaurants
//http PATCH localhost:8080/restaurants/1/menuitems < menuitems.json