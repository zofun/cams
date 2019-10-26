package com.shente.cams.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TResult {


    private Integer rId;

    @JsonProperty("course_id")
    private Integer courseId;
    private String account;

    @JsonProperty("result_data")
    private String resultData;
    private Character resultType;
}
