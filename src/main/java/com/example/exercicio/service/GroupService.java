package com.example.exercicio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exercicio.model.Group;
import com.example.exercicio.repository.GroupRepository;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public List<Group> findGroupWithUsers() {
        return groupRepository.findGroupWithUsers();
    }
    
}
