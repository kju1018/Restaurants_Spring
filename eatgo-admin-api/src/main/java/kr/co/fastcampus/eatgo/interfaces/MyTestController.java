package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.MyTestService;
import kr.co.fastcampus.eatgo.domain.MyTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class MyTestController {
    @Autowired
    private MyTestService myTestService;

    @GetMapping("/mytest")
    private List<MyTest> list(){
        List<MyTest> myTests = myTestService.getMyTests();
        return myTests;
    }

    @PostMapping("/mytest")
    public ResponseEntity<?> create(
            @RequestBody MyTest resource
    ) throws URISyntaxException {

        MyTest myTest = myTestService.addMyTest(
                MyTest.builder().name(resource.getName())
                .level(1L).build());

        URI location = new URI("/mytest/" + myTest.getId());
        return ResponseEntity.created(location).body("{}");
    }

}
