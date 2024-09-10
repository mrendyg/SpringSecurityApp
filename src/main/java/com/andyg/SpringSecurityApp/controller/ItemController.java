package com.andyg.SpringSecurityApp.controller;

import com.andyg.SpringSecurityApp.persistence.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/item")
@PreAuthorize("denyAll()")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

}
