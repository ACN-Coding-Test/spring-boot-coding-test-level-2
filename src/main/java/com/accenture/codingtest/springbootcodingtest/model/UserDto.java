package com.accenture.codingtest.springbootcodingtest.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String username;
    private String password;
}
