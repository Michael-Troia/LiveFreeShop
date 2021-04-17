package com.example.livefree.Controllers;
import com.example.livefree.Repositories.ItemRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ItemRepo itemRepo;

    public HomeController(ItemRepo itemRepo){
        this.itemRepo = itemRepo;
    }

    @GetMapping("/inventory")
    public String showHome(Model viewModel) {
        viewModel.addAttribute("items", itemRepo.findAll());
        return "index";
    }
}