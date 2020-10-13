package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuitemService {
    @Autowired
    private MenuItemRepository menuitemRepository;

    //Todo : 왜 생성자로 만들어줘야하는지 다시 찾아보기
    public MenuitemService(MenuItemRepository menuitemRepository) {
        this.menuitemRepository = menuitemRepository;
    }

    public List<MenuItem> getMenuItems(long restaurantId) {
        return menuitemRepository.findAllByRestaurantId(restaurantId);

    }

    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {

        for(MenuItem menuItem : menuItems) {
            update(restaurantId, menuItem);
        }
    }

    private void update(Long restaurantId, MenuItem menuItem) {
        if(menuItem.isDestroy()){
            menuitemRepository.deleteById(menuItem.getId());
            return;
        }
        menuItem.setRestaurantId(restaurantId);
        menuitemRepository.save(menuItem);
    }
}
