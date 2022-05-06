package WarehouseAPI.WarehouseAPI.controller.warehouse;

import WarehouseAPI.WarehouseAPI.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:9090/");
        return isAdded ? new ResponseEntity<>(null, headers, HttpStatus.FOUND) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(name = "showcaseId") Long showcaseId,
                                    @RequestParam(name = "itemId") Long itemId) {
        final boolean isDeleted = warehouseService.removeItemFromShowcase(showcaseId, itemId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:9090/");
        return isDeleted ? new ResponseEntity<>(null, headers, HttpStatus.FOUND) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
