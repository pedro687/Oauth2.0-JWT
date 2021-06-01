package com.spiet.creepy.dtos;


import com.spiet.creepy.models.enums.GenType;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotNull
    @Size(min = 11, max = 11)
    private String tellphone;

    @NotNull
    private Character sexo;

}
