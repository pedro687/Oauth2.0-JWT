package com.spiet.creepy.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

}
