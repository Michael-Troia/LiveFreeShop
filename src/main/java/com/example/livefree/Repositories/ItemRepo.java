package com.example.livefree.Repositories;

import com.example.livefree.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository <Item, Long> {

}
