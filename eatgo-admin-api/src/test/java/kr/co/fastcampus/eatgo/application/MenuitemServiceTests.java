package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MenuitemServiceTests {

    private MenuitemService menuitemService;

    //Todo : Mock가 뭔지 다시 정확히 찾아보기
    @Mock
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        menuitemService = new MenuitemService(menuItemRepository);
    }

    @Test
    public void getMenuItems(){
        List<MenuItem> mockMenuItems = new ArrayList<>();
        mockMenuItems.add(MenuItem.builder().name("Kimchi").build());

        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(mockMenuItems);

        List<MenuItem> menuItems = menuitemService.getMenuItems(1004L);

        MenuItem menuItem = menuItems.get(0);

        assertThat(menuItem.getName()).isEqualTo("Kimchi");
    }


    @Test
    public void bulkUpdate(){
        List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(MenuItem.builder().name("Gukbob").build());//첫번째 호출
        menuItems.add(MenuItem.builder().id(12L).name("Kimchi").build());//두번째 호출
        menuItems.add(MenuItem.builder().id(1004L).destroy(true).build());//첫번째 호출

        //1. menuItems에 3개를 추가하고 menuitemService.bulkUpdate(1L, menuItems);
        //2. 이것을 실행했을때
        menuitemService.bulkUpdate(1L, menuItems);

        //3. 원하는 메소드가 특정조건으로 실행되었는지 검사 (두번 호출 했는지 검사)
        verify(menuItemRepository, times(2)).save(any());
        verify(menuItemRepository, times(1)).deleteById(eq(1004L));
    }

}