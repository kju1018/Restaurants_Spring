package kr.co.fastcampus.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAll();

    //Restaurant이라는게 Restaurant 인스턴스 또는 null로 처리 Optional은 null을 처리하지 않음 Restaurant가 있냐 없냐로 구분
    Optional<Restaurant> findById(Long id);

//    Restaurant save(Restaurant restaurant);
}
