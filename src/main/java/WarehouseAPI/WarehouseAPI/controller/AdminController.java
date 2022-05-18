package WarehouseAPI.WarehouseAPI.controller;

import WarehouseAPI.WarehouseAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String userList(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "admin";
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("users", userService.getUserList(userId));
        return "admin";
    }

    @GetMapping("/setRole")
    public String setRole(@RequestParam(required = true, defaultValue = "") Long userId,
                          Model model) {
        model.addAttribute("roles", userService.allRoles());
        return "setRolesPage";
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam Long userId) {
        final boolean isDelete = userService.deleteUser(userId);
        return isDelete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("/setRole")
    public ResponseEntity<?> setRole(@RequestParam Long userId,
                                     @RequestParam Long roleId) {

        userService.setRole(userId, roleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

