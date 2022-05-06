package WarehouseAPI.WarehouseAPI.controller.warehouse;

import WarehouseAPI.WarehouseAPI.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouse")
public class WarehouseRestController {
    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestParam(name = "showcaseId") Long showcaseId,
                                 @RequestParam(name = "itemId") Long itemId,
                                 @RequestParam(name = "quantity", defaultValue = "0") int quantity) {
        final boolean isAdded = warehouseService.addItemOnShowcase(showcaseId, itemId, quantity);
        return isAdded ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(name = "showcaseId") Long showcaseId,
                                    @RequestParam(name = "itemId") Long itemId) {
        final boolean isDeleted = warehouseService.removeItemFromShowcase(showcaseId, itemId);
        return isDeleted ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
