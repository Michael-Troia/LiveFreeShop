package com.example.livefree.Controllers;

import com.example.livefree.Models.Item;
import com.example.livefree.Models.Order;
import com.example.livefree.Models.User;
import com.example.livefree.Repositories.ItemRepo;
import com.example.livefree.Repositories.OrderRepo;
import com.example.livefree.Repositories.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {
    private final ItemRepo itemRepo;
    private final UserRepo userRepo;
    private final OrderRepo orderRepo;

    public OrderController(ItemRepo itemRepo, UserRepo userRepo, OrderRepo orderRepo){
        this.itemRepo = itemRepo;
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
    }

//    View items to order
    @GetMapping("/item/order")
    public String showItems(Model viewModel){
        viewModel.addAttribute("items", itemRepo.findAll());
        return "item/order";
    }

//    Place order
    @GetMapping("/item/order/{id}")
    public String showOrderForm(Model viewModel,
                                @PathVariable long id){
        viewModel.addAttribute("item", itemRepo.findById(id));
        viewModel.addAttribute("newOrder", new Order());
        return "item/place-order";
    }

    @PostMapping("/item/order/{id}")
    public String showOrderForm(@PathVariable long id,
                                @ModelAttribute Order order,
                                @ModelAttribute User user){
        Item item = itemRepo.findById(id);
        Order newOrder = new Order();
        newOrder.setUser(user);
        newOrder.setItem(item.getName());
        newOrder.setCost(item.getCost() * order.getQuantity());
        newOrder.setQuantity(order.getQuantity());
        orderRepo.save(newOrder);
        item.setQuantity(item.getQuantity() - order.getQuantity());
        itemRepo.save(item);

        return "redirect:/inventory";
    }

    @GetMapping("/item/order/view")
    public String viewOrders(Model viewModel){
        viewModel.addAttribute("orders", orderRepo.findAll());
        return "item/view-orders";
    }
}
