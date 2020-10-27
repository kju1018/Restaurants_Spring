package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.MyTest;
import kr.co.fastcampus.eatgo.domain.MyTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyTestService {

    private MyTestRepository myTestRepository;

    @Autowired
    public MyTestService(MyTestRepository myTestRepository) {
        this.myTestRepository = myTestRepository;
    }

    public List<MyTest> getMyTests() {
        List<MyTest> myTests = myTestRepository.findAll();
        return myTests;
    }

    public MyTest addMyTest(MyTest myTest) {
        return myTestRepository.save(myTest);
    }
}
