package com.example.thymeleafdemo;

import com.example.thymeleafdemo.DTO.UserDTO;
import com.example.thymeleafdemo.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThymeleafDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafDemoApplication.class, args);
        UserRepository userRepository = UserRepository.getInstance();
        userRepository.getUsers().add(new UserDTO("mark", "mark"));
        userRepository.getUsers().add(new UserDTO("jonatan", "jonatan"));
    }

}
