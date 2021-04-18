package com.example.livefree.Controllers;
import com.example.livefree.Models.Item;
import com.example.livefree.Repositories.ItemRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final ItemRepo itemRepo;

    public HomeController(ItemRepo itemRepo){
        this.itemRepo = itemRepo;
    }

    @GetMapping("/inventory")
    public String showHome(Model viewModel) {
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