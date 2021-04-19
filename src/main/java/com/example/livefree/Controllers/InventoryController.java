package com.example.livefree.Controllers;
import com.example.livefree.Models.Item;
import com.example.livefree.Models.User;
import com.example.livefree.Repositories.ItemRepo;
import com.example.livefree.Repositories.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Controller
public class InventoryController {

    private final ItemRepo itemRepo;
    private final UserRepo userRepo;

    public InventoryController(ItemRepo itemRepo, UserRepo userRepo){
        this.itemRepo = itemRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/inventory")
    public String showHome(Model viewModel) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        viewModel.addAttribute("user", username);
        viewModel.addAttribute("items", itemRepo.findAll());
        viewModel.addAttribute("newItem", new Item());
        return "index";
    }

    @PostMapping("/item/create")
    public String createItem(@ModelAttribute Item item) {
        itemRepo.save(item);
        return "redirect:/inventory";
    }

    @GetMapping("/item/edit/{id}")
    public String showEditItem(Model viewModel,
                               @PathVariable long id){
        Item item = itemRepo.findById(id);
        viewModel.addAttribute("item", item);
        return "item/edit";
    }

    @PostMapping("/item/edit/{id}")
    public String editItem(@ModelAttribute Item item,
                           @PathVariable long id) {
        item.setId(id);
        itemRepo.save(item);
        return "redirect:/inventory";
    }

    @PostMapping("/item/delete/{id}")
    public String deleteItem(@PathVariable long id){
        Item item = itemRepo.findById(id);
        itemRepo.delete(item);
        return "redirect:/inventory";
    }
}