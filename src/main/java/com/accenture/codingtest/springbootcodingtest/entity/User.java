package com.accenture.codingtest.springbootcodingtest.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="User")
@JsonSerialize
public class User {

    @Id
    private String id;

    // @Required
    private String username;

    //@Required
    private String password;

}
