package com.example.thymeleafdemo.repository;

import com.example.thymeleafdemo.DTO.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
public class UserRepository {
    private static UserRepository single_instance = null;
    private ArrayList<UserDTO> users = new ArrayList<>();
    public static UserRepository getInstance(){
        if(single_instance == null)
            single_instance = new UserRepository();
        return single_instance;
    }
}
