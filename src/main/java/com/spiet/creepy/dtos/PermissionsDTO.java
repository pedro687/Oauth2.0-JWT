package com.spiet.creepy.dtos;

import com.spiet.creepy.models.enums.PermissionsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PermissionsDTO {
    private Long id;

    @Enumerated(EnumType.STRING)
    private PermissionsEnum description;
}
