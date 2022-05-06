package WarehouseAPI.WarehouseAPI.controller.warehouse;

import WarehouseAPI.WarehouseAPI.entity.ShowcasesItem;
import WarehouseAPI.WarehouseAPI.service.ItemService;
import WarehouseAPI.WarehouseAPI.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/warehouse")
public class WarehouseViewController {
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    ItemService itemService;

    @GetMapping("/addItem")
    public String addOnShowcaseView(Model model) {
        model.addAttribute("allItems", itemService.readAll());
        return "addItemOnShowcasePage";
    }

    @GetMapping("/getItems")
    public String showcasesItemsList(@RequestParam(required = true) Long showcaseId,
                                     Model model) {
        List<ShowcasesItem> items = warehouseService.getShowcasesItems(showcaseId);
        model.addAttribute("allShowcasesItems", items);
        return "showcasesItemsPage";
    }
}
