package com.example.exercicio.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exercicio.model.Group;
import com.example.exercicio.repository.GroupRepository;
import com.example.exercicio.service.GroupService;


@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupService groupService;

    @GetMapping("/list-users")
    public ResponseEntity<Iterable<GroupDTO>> listGroupsWithUsers() {
        return ResponseEntity.ok(groupService.findGroupWithUsers()
                                    .stream()
                                    .map((g) -> 
                                        entityToDTO(Optional.of(g)))
                                    .collect(Collectors.toList())
                                );
    }

    public GroupDTO entityToDTO(Optional<Group> group) {
        GroupDTO groupDTO = null;
        if (group.isPresent()) {
            Group groupEntity = group.get();
            groupDTO = new GroupDTO(
                groupEntity.getName(), 
                groupEntity.getCode(), 
                groupEntity.getUsers()
                    .stream()
                    .map(u -> new UserDTO(u.getName(), u.getEmail()))
                    .collect(Collectors.toList())
            );
        }

        return groupDTO;
    }


    public class GroupDTO {
        private final String name;
        private final String code;
        private final List<UserDTO> list;
        
        public GroupDTO(String name, String code, List<UserDTO> list) {
            this.name = name;
            this.code = code;
            this.list = list;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }

        public List<UserDTO> getList() {
            return list;
        }
                        
    }

    public class UserDTO {
        private final String name;
        private final String email;

        public UserDTO(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
                               
    }


}
