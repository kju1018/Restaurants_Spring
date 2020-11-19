package kr.co.fastcampus.eatgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EatgoLoginApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EatgoLoginApiApplication.class, args);
    }


}
//http localhost:8080/restaurants
//http PATCH localhost:8080/restaurants/1/menuitems < menuitems.json

//eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjUxNywibmFtZSI6IlRlc3RlciJ9.oNt0AlP91QntCF67ONupyIXrmts9-r_ClW56GDABjeA