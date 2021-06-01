package com.spiet.creepy.models;

import com.spiet.creepy.models.enums.GenType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", unique=true)
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "username")
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_permissions",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_permission"))
    private List<Permissions> permissions;

    @Column(name = "telephone", length = 11)
    private String tellphone;


    @Column(name = "sexo")
    private Character sexo;
}
