package com.example.exercicio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exercicio.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long>{
    List<Group> findByCode(String code);
}
