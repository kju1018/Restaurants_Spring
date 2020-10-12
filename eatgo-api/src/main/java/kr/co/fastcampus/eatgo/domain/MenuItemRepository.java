package kr.co.fastcampus.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    List<MenuItem> findAllByRestaurantId(Long restaurantId);
    //findAllByRestaurantId에서 RestaurantId는 Menuitem에 private Long restaurantId;
    //이것을 의미 restaurantid라고 I를 소문자로 바꿔도 인지를 못함
    //하지만 findAllByRestaurantId에서 RestaurantId는 대분자 R이지만
    //Menuitem 클래스에서 restaurantId은 소문자 r 상관없음 즉 시작단어는 상관없음

    void deleteById(Long id);

}
