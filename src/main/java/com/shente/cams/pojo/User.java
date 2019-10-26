package com.shente.cams.pojo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    private Integer uId;
    private String account;
    private String password;


}
