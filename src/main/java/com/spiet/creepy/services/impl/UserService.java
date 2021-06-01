package com.spiet.creepy.services.impl;

import com.spiet.creepy.dtos.UsersDTO;
import com.spiet.creepy.exceptions.EmailAlreadyExistsException;
import com.spiet.creepy.models.Users;
import com.spiet.creepy.repositories.UserRepository;
import com.spiet.creepy.services.IUsersService;
import com.spiet.creepy.utils.UsersConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements IUsersService {

    private UserRepository userRepository;

    private UsersConverter converter;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UsersConverter usersConverter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.converter = usersConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UsersDTO createUser(UsersDTO usersDTO) {
        if (usersDTO.getSexo() != 'M' || usersDTO.getSexo() != 'F'  || usersDTO.getSexo() != 'O') {
            throw new IllegalArgumentException("Invalid Sex");
        }

        if (existsByEmail(usersDTO.getEmail())) {
            log.error("Email já existente na base dados! {}", usersDTO.getEmail());
            throw new EmailAlreadyExistsException("Este Email já pertence a um usuário!");
        }

        Users user = converter.toEntity(usersDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        return converter.toDto(user);
    }


}
