package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.MenuitemService;
import kr.co.fastcampus.eatgo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    private MenuitemService menuitemService;

    @GetMapping("/restaurants/{restaurantId}/menuitems")
    public List<MenuItem> list(@PathVariable("restaurantId") Long restaurantId){
        List<MenuItem> menuItems = menuitemService.getMenuItems(restaurantId);

        return menuItems;
    }


    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    public String bulkUpdate(@PathVariable("restaurantId") Long restaurantId, @RequestBody List<MenuItem> menuItems){
        menuitemService.bulkUpdate(restaurantId, menuItems);
        return "";
    }
}
