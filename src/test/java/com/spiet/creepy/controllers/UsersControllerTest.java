package com.spiet.creepy.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spiet.creepy.dtos.UsersDTO;
import com.spiet.creepy.models.Users;
import com.spiet.creepy.models.enums.GenType;
import com.spiet.creepy.repositories.UserRepository;
import com.spiet.creepy.services.IUsersService;
import com.spiet.creepy.utils.UsersConverter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UsersControllerTest {

    static String BASE_URL = "/users";
    static String OAUTH_URL = "/oauth/token";

    @MockBean
    IUsersService service;

    @Autowired
    MockMvc mvc;

    @Autowired
    UsersConverter usersConverter;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    PasswordEncoder passwordEncode;

    public UsersDTO createUserDTO() {
        UsersDTO userDTO = new UsersDTO();
        userDTO.setEmail("pedrospissadsets@gmail.com");
        userDTO.setUsername("Pedro Emanoel");
        userDTO.setPassword(passwordEncode.encode("12345"));
        userDTO.setTellphone("13996403088");
        userDTO.setSexo('M');

        return userDTO;
    }

    @BeforeEach
    public void setUp() {
        service.createUser(createUserDTO());
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

    @Test
    @DisplayName("Deve efetuar login")
    void shouldLogin() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client", "angular");
        params.add("username", createUserDTO().getEmail());
        params.add("password", "12345");

        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.post(OAUTH_URL)
                .params(params)
                .with(httpBasic("angular","react123"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        var result = mvc.perform(req)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("access_token").exists());
    }
}
