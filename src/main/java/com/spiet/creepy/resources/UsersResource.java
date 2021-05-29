package com.spiet.creepy.resources;

import com.spiet.creepy.dtos.UsersDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(path = "/users")
public interface UsersResource {

    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody @Valid UsersDTO userDTO);

    @GetMapping("/bla")
    public ResponseEntity<String> test();
}
