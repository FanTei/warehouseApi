package WarehouseAPI.WarehouseAPI.controller;

import WarehouseAPI.WarehouseAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminViewController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public String userList(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "admin";
    }

    @GetMapping("/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("users", userService.getUserList(userId));
        return "admin";
    }

    @GetMapping("/setRole")
    public String setRole(@RequestParam(required = true, defaultValue = "") Long userId,
                          Model model) {
        model.addAttribute("roles", userService.allRoles());
        return "setRolesPage";
    }
}
