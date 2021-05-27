package com.spiet.creepy.models;

import com.spiet.creepy.models.enums.PermissionsEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "permissions")
@Data
@EqualsAndHashCode
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PermissionsEnum description;
}
