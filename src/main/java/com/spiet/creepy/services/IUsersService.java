package com.spiet.creepy.services;

import com.spiet.creepy.dtos.UsersDTO;

public interface IUsersService {
     Boolean existsByEmail(String email);
     UsersDTO createUser(UsersDTO usersDTO);
}
