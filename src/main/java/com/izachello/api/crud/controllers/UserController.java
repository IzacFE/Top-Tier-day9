package com.izachello.api.crud.controllers;

import com.izachello.api.crud.dtos.UserDTO;
import com.izachello.api.crud.models.User;
import com.izachello.api.crud.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    ModelMapper modelMapper = new ModelMapper();

    private User convertUserToEntity (UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }

    private UserDTO convertUserToDTO (User name){
        return modelMapper.map(name, UserDTO.class);
    }

    //    API CREATE User
    @PostMapping("/post/name/")
    public Map<String, Object> createName(@RequestBody UserDTO userDTO){
        Map<String, Object> mapResult = new HashMap<>();

        User name = convertUserToEntity(userDTO);
        name.setName(userDTO.getUser());

        mapResult.put("name","User Create Success");
        mapResult.put("data",userRepository.save(name));

        return mapResult;
    }

    //    API READ ALL Users
    @GetMapping("/get/users")
    public Map<String, Object> getAllNames(){
        Map<String, Object> mapResult = new HashMap<>();
        List<UserDTO> listUsersDTO = new ArrayList<>();

        for (User name : userRepository.findAll()){
            UserDTO userDTO = convertUserToDTO(name);
            listUsersDTO.add(userDTO);
        }

        String name;
        if(listUsersDTO.isEmpty()){
            name="Data is empty";
        }else{
            name="Show all data";
        }

        mapResult.put("name", name);
        mapResult.put("data",listUsersDTO);
        mapResult.put("total",listUsersDTO.size());

        return mapResult;
    }

    //    API UPDATE User
    @PutMapping("/put/name")
    public  Map<String, Object> updateUser (@RequestParam(value ="userId") long userId, @RequestBody UserDTO userDTO){
        Map<String, Object> mapResult = new HashMap<>();

        User name = userRepository.findById(userId).orElse(null);

        name.setName(userDTO.getUser());

        mapResult.put("name","User Update success");
        mapResult.put("data", userRepository.save(name));

        return mapResult;
    }

    //    API DELETE User
    @DeleteMapping("/delete/name/{userId}")
    public  Map<String, Object> deleteUser(@PathVariable(value = "userId") long userId){
        Map<String, Object> mapResult = new HashMap<>();

        User name = userRepository.findById(userId).orElse(null);

        userRepository.delete(name);

        mapResult.put("name","User Delete Success");

        return  mapResult;
    }
}

