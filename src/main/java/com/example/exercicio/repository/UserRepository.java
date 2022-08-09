package com.example.exercicio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.exercicio.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
