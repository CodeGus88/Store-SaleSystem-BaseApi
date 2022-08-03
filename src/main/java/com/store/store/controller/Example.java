package com.store.store.controller;


import com.store.store.entities.Client;
import com.store.store.repository.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/example")
public class Example {

    @GetMapping("{id}")
    public Client example(@PathVariable long id) throws Exception {
        return new ClientRepository().findById(id);
    }

}
