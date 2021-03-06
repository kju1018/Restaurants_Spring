package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantNotFoundException;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class restaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;


    //모든 테스트가 실행되기 전에 반드시 한번씩 실행됨
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();
        //스프링을 이용한 테스트가 아니기때문에 직접 의존성 주입
        restaurantService = new RestaurantService(
                restaurantRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .categoryId(1L)
                .name("Bob zip")
                .address("Seoul")
                .build();
//                new Restaurant(1004L, "Bop zip", "Seoul");
        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);//!!!!!이거랑

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
    }


    @Test
    public void getRestaurants(){//!!!!!여기 메소드는 연관이 있음
        //이게 동작되는 원리
        //restaurantService.getRestaurants(); 이걸 실행하게 되면
        //restaurantRepository.findAll() 이게 실행됨
        //그러면 위에 given(restaurantRepository.findAll()).willReturn(restaurants); 이게 실행되면서
        //위에 restaurants 반환해줌줌
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId()).isEqualTo(1004L);

    }

    @Test
    public void getRestaurantWithExisted(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
        //검사해줄 명령어

        assertThat(restaurant.getId()).isEqualTo(1004L);


    }

    @Test()
    public void getRestaurantWithNotExisted(){
//        restaurantService.getRestaurant(404L); 가 실행되면
//        RestaurantNotFoundException.class 가 실행되야함

        assertThrows(RestaurantNotFoundException.class, () -> {
            restaurantService.getRestaurant(404L);
        });


    }

    @Test
    public void addRestaurant(){
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                .name("BeRyong")
                .address("Busan")
                .build();

        //given은 restaurantRepository.save(any())를 실행하면 saved를 리턴한다는 뜻
//        given(restaurantRepository.save(any())).willReturn(saved);

        Restaurant created = restaurantService.addRestaurant(restaurant);

        assertThat(created.getId()).isEqualTo(1234);
    }

    @Test
    public void updataRestaurant(){
        //조건같은느낌
        //미리 변경될 restaurant를 만들어주고
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        //restaurantRepository.findById(1004L)이게 실행되면 Optional.of(restaurant)을 리턴해준다
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

        //실행
        restaurantService.updateRestaurant(1004L, "Sool zip", "Busan");

        assertThat(restaurant.getName()).isEqualTo("Sool zip");
        assertThat(restaurant.getAddress()).isEqualTo("Busan");

    }
}