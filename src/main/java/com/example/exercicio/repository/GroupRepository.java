package com.example.exercicio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.exercicio.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long>{
    @Query("SELECT g FROM Group g  JOIN FETCH g.users u ORDER BY g.name DESC")
    List<Group> findGroupWithUsers();

    List<Group> findByCode(String code);
}
