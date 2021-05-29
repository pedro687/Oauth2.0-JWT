package com.spiet.creepy.services;

import com.spiet.creepy.dtos.UsersDTO;

public interface IUsersService {
    public Boolean existsByEmail(String email);
    public UsersDTO createUser(UsersDTO usersDTO);
}
