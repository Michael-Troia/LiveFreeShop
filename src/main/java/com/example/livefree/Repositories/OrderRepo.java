package com.example.livefree.Repositories;

import com.example.livefree.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
