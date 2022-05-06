package WarehouseAPI.WarehouseAPI.controller.item;

import WarehouseAPI.WarehouseAPI.entity.Item;
import WarehouseAPI.WarehouseAPI.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<Item> read(@PathVariable(name = "id") Long id) {
        final Item item = itemService.read(id);
        return item != null
                ? new ResponseEntity<Item>(item, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<String> delete(@RequestParam(required = true, defaultValue = "") Long id,
                                         @RequestParam(required = true, defaultValue = "") String action,
                                         Model model) {
        final boolean isDelete = false;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:9090/");
        return isDelete
                ? new ResponseEntity<>(null, headers, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@ModelAttribute("itemForm") Item item) {
        itemService.create(item);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:9090/");
        return new ResponseEntity<>(null, headers, HttpStatus.FOUND);
    }

    @PostMapping("/update")
    public ResponseEntity<Item> update(@RequestParam(name = "itemId") Long itemId,
                                       @ModelAttribute Item item) {
        final boolean update = itemService.update(item, itemId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:9090/");
        return update
                ? new ResponseEntity<>(null, headers, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
