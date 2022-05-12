package WarehouseAPI.WarehouseAPI.controller;

import WarehouseAPI.WarehouseAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam Long userId) {
        final boolean isDelete = userService.deleteUser(userId);
        return isDelete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/setRole")
    public ResponseEntity<?> setRole(@RequestParam Long userId,
                                     @RequestParam Long roleId) {

        userService.setRole(userId, roleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

