package com.spiet.creepy.utils;

import com.spiet.creepy.dtos.UsersDTO;
import com.spiet.creepy.models.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UsersConverter {

    public Users toEntity(UsersDTO usersDTO) {
        Users users = new Users();
        users.setEmail(usersDTO.getEmail());
        users.setUsername(usersDTO.getUsername());
        users.setPassword(usersDTO.getPassword());
        users.setSexo(usersDTO.getSexo());
        users.setTellphone(usersDTO.getTellphone());

        return users;
    }

    public UsersDTO toDto(Users users) {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setEmail(users.getEmail());
        usersDTO.setUsername(users.getUsername());
        usersDTO.setPassword(users.getPassword());
        usersDTO.setSexo(users.getSexo());
        usersDTO.setTellphone(users.getTellphone());

        return usersDTO;
    }
}
