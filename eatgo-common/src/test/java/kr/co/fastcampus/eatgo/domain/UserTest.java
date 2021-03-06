package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    public void creation(){
        User user = User.builder()
                .email("tester@eample.com")
                .name("테스터")
                .level(3L)
                .build();

        assertThat(user.getName()).isEqualTo("테스터");
        assertThat(user.isAdmin()).isEqualTo(true);

        assertThat(user.isActive()).isEqualTo(true);

        user.deactivate();

        assertThat(user.isActive()).isEqualTo(false);
    }

    @Test
    public void restaurantOwner(){
        User user = User.builder().level(1L).build();

        assertThat(user.isRestaurantOwer()).isEqualTo(false);

        user.setRestaurantId(1004L);

        assertThat(user.isRestaurantOwer()).isEqualTo(true);
        assertThat(user.getRestaurantId()).isEqualTo(1004L);

    }

}