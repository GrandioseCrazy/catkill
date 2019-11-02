package com.avaj.ekill.model;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
@Data
@Mapper
public class User {
    private Long id;

    private String nickname;

    private String password;

    private String salt;

    private String head;

    private Date registerDate;

    private Date lastLoginDate;

    private Integer loginCount;

}