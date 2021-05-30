package com.spiet.creepy.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spiet.creepy.dtos.UsersDTO;
import com.spiet.creepy.services.IUsersService;
import com.spiet.creepy.services.impl.UserService;
import com.spiet.creepy.utils.UsersConverter;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UsersControllerTest {

    static String BASE_URL = "/users";

    @MockBean
    IUsersService service;

    @Autowired
    MockMvc mvc;

    @Autowired
    UsersConverter usersConverter;

    @Autowired
    ObjectMapper mapper;

    public UsersDTO createUserDTO() {
        UsersDTO userDTO = new UsersDTO();
        userDTO.setEmail("JonDoe@email.com");
        userDTO.setUsername("Jon Doe");
        userDTO.setPassword("12345");
        return userDTO;
    }

    @Test
    @DisplayName("Deve Registrar um usuário")
    void shouldCreateAnUser() throws Exception{
        System.out.println(createUserDTO());
        String json = mapper.writeValueAsString(createUserDTO());

        BDDMockito.given(service.createUser(Mockito.any(UsersDTO.class)))
                .willReturn(createUserDTO());

        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.post(BASE_URL.concat("/register"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

            mvc.perform(req)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("username").value(createUserDTO().getUsername()));
    }
}
