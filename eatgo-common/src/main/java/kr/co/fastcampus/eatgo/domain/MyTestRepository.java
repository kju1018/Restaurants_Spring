package kr.co.fastcampus.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MyTestRepository extends CrudRepository<MyTest, Long> {
    List<MyTest> findAll();
}
