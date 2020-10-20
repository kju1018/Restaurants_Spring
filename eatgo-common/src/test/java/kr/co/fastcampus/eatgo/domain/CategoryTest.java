package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryTest {

    @Test
    public void creation(){
        Category category = Category.builder().name("Korean").build();

        assertThat(category.getName()).isEqualTo("Korean");
    }

}