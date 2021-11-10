package com.example.thymeleafdemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
public class UserDTO {
    private static int id = 0;
    private String username;
    private String password;
    private UUID session;
    public UserDTO(String username, String password){
        this.username = username;
        this.password = password;
        this.id++;
    }

    @Override
    public String toString(){
        return id+" : " + username;
    }
}
