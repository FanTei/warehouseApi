package WarehouseAPI.WarehouseAPI.controller;

import WarehouseAPI.WarehouseAPI.entity.Item;
import WarehouseAPI.WarehouseAPI.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("")
    public String itemList(Model model) {
        List<Item> items = itemService.readAll();
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/update")
    public String updatePage(Model model,
                             @RequestParam Long itemId) {
        Item updatingItem = itemService.read(itemId);
        model.addAttribute("item", updatingItem);
        return "editItemPage";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("item", new Item());
        return "createItemPage";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> read(@PathVariable Long id) {
        final Item item = itemService.read(id);
        return item != null
                ? new ResponseEntity<>(item, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/create")
    public String create(@Valid Item item, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "createItemPage";
        itemService.create(item);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@RequestParam Long itemId,
                         @Valid Item item,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors())
            return "editItemPage";
        final boolean update = itemService.update(item, itemId);
        return "redirect:/";
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestParam Long id) {
        final boolean isDelete = itemService.delete(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:9090/");
        return isDelete
                ? new ResponseEntity<>(null, headers, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
