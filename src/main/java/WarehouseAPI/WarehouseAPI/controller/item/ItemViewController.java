package WarehouseAPI.WarehouseAPI.controller.item;

import WarehouseAPI.WarehouseAPI.entity.Item;
import WarehouseAPI.WarehouseAPI.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.HttpMethodConstraint;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemViewController {
    @Autowired
    ItemService itemService;

    @GetMapping("")
    public String itemList(Model model) {
        List<Item> items = itemService.readAll();
        model.addAttribute("allItems", items);
        return "items";
    }

    @GetMapping("/update")
    public String updatePage(Model model,
                             @RequestParam Long itemId) {
        Item updatingItem = itemService.read(itemId);
        model.addAttribute("itemForm", updatingItem);
        return "interactionItemPage";
    }
    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("itemForm", new Item());
        return "interactionItemPage";
    }
}
