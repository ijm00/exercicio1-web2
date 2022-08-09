package com.example.exercicio.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.exercicio.model.User;
import com.example.exercicio.repository.UserRepository;

import ch.qos.logback.classic.Logger;

@RestController
public class UserController {
    private final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping({ "/client/list" })
    public ResponseEntity<Iterable<User>> getUsers() {
        LOGGER.info("Lista de usuários fornecida.");
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping({ "/client" })
    public ResponseEntity<Void> userCreate(@Valid @RequestBody User user) {
        userRepository.save(user);

        LOGGER.warn("USER CRIADO :: " + user);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @PutMapping({ "/client/{id}" })
    public ResponseEntity<Void> userUpdate(@Valid @PathVariable(value = "id") long id, @Valid @RequestBody User user) {
        user.setId(id);
        userRepository.save(user);

        LOGGER.warn("USER Atualizado :: " + user);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @DeleteMapping({ "/client/{id}" })
    public ResponseEntity<Void> userDelete(@PathVariable(value = "id") long id) {
        var user = userRepository.findById(id);
        userRepository.deleteById(id);
        LOGGER.warn("USER removido :: " + user);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return errors;

    }

}
