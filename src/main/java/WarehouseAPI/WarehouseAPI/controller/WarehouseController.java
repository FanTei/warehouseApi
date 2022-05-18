package WarehouseAPI.WarehouseAPI.controller;

import WarehouseAPI.WarehouseAPI.entity.ShowcasesItem;
import WarehouseAPI.WarehouseAPI.service.ItemService;
import WarehouseAPI.WarehouseAPI.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    ItemService itemService;

    @GetMapping("/addItem")
    public String addOnShowcaseView(Model model) {
        model.addAttribute("items", itemService.readAll());
        return "addItemOnShowcasePage";
    }

    @GetMapping("/getItems")
    public String showcasesItemsList(@RequestParam Long showcaseId,
                                     Model model) {
        List<ShowcasesItem> items = warehouseService.getShowcasesItems(showcaseId);
        model.addAttribute("showcasesItems", items);
        return "showcasesItemsPage";
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestParam(name = "showcaseId") Long showcaseId,
                                 @RequestParam(name = "itemId") Long itemId,
                                 @RequestParam(name = "quantity") int quantity) {
        final boolean isAdded = warehouseService.addItemOnShowcase(showcaseId, itemId, quantity);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:9090/");
        return isAdded ? new ResponseEntity<>(null, headers, HttpStatus.FOUND) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping()
    public ResponseEntity<?> delete(@RequestParam(name = "showcaseId") Long showcaseId,
                                    @RequestParam(name = "itemId") Long itemId) {
        final boolean isDeleted = warehouseService.removeItemFromShowcase(showcaseId, itemId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:9090/");
        return isDeleted ? new ResponseEntity<>(null, headers, HttpStatus.FOUND) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
