package com.shente.cams.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private Integer uId;
    @JsonProperty("username")
    private String account;
    private String password;


}
