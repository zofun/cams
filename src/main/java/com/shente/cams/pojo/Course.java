package com.shente.cams.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class Course {


    @JsonProperty("c_id")
    private Integer cId;
    private Integer userId;
    private String weeks;
    private Integer hours;

    @JsonProperty("start_monday")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date startMonday;
    private String name;
    private int state;
}
