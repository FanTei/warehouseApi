package WarehouseAPI.WarehouseAPI.controller.item;

import WarehouseAPI.WarehouseAPI.entity.Item;
import WarehouseAPI.WarehouseAPI.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> delete(@RequestParam(required = true, defaultValue = "") Long id,
                                    @RequestParam(required = true, defaultValue = "") String action,
                                    Model model) {
        final boolean isDelete = itemService.delete(id);
        return isDelete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@ModelAttribute("itemForm") Item item) {
        itemService.create(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Item> update(@RequestParam(name = "itemId") Long itemId,
                                       @ModelAttribute Item item) {
        final boolean update = itemService.update(item, itemId);
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

//    @PostMapping(value = "/delete")
//    public String delete(@RequestParam(required = true, defaultValue = "") Long id,
//                         @RequestParam(required = true, defaultValue = "") String action,
//                         Model model) {
//        if (action.equals("delete"))
//            itemService.delete(id);
//        return "redirect:/items";
//    }
//
//    @PostMapping("/update")
//    public String update(@RequestParam Long id, @RequestBody Item item) {
//        final boolean update = itemService.update(item, id);
//        return "redirect:/";
//    }
//}
//    @GetMapping("/items")
//    public ResponseEntity<?> itemList(Model model) {
//        List<Item> items = itemService.readAll();
//        return items != null && !items.isEmpty()
//                ? new ResponseEntity<>(items, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
