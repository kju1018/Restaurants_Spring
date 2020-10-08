package kr.co.fastcampus.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    List<MenuItem> findAllByrestaurantId(Long restaurantId);
    //findAllByrestaurantId에서 restaurantId는 Menuitem에 private Long restaurantId;
    //이것을 의미 restaurantid라고 I를 소문자로 바꿔도 인지를 못함

    void deleteById(Long id);

}
