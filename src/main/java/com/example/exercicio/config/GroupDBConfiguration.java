package com.example.exercicio.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.example.exercicio.model.Group;
import com.example.exercicio.repository.GroupRepository;

@Configuration
public class GroupDBConfiguration {
    @Autowired
    private GroupRepository groupRepository;

    @PostConstruct
    public void initDB() {
        var groupUser = new Group();
		groupUser.setName("Usuários");
		groupUser.setCode("users");
		groupRepository.save(groupUser);

        var groupAdmin = new Group();
		groupAdmin.setName("Usuários");
		groupAdmin.setCode("admins");
		groupRepository.save(groupAdmin);
    }
}
