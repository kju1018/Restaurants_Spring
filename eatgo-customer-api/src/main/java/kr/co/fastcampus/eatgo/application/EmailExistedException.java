package kr.co.fastcampus.eatgo.application;

public class EmailExistedException extends RuntimeException{

    EmailExistedException(String eamil){
        super("Email is already registered: " + eamil);
    }
}
