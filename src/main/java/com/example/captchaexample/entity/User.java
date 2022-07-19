package com.example.captchaexample.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String email;

    //usr kirigan string qiymat
    @Transient
    private String captcha;

    //
    @Transient
    private String hiddenCaptcha;

    //rasmi
    @Transient
    private String realCaptcha;
}
