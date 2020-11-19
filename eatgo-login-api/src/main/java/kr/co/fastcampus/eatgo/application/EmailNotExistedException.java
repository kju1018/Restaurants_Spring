package kr.co.fastcampus.eatgo.application;

public class EmailNotExistedException extends RuntimeException{

    EmailNotExistedException(String eamil){
        super("Email is not registered: " + eamil);
    }
}
