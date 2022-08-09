package com.example.exercicio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exercicio.model.User;

@RestController
public class HomeControler {

    @GetMapping({ "/" })
    public ResponseEntity<User> home(@RequestParam(value = "showUser", defaultValue = "false") boolean showUser) {
        if (showUser) {
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

}
