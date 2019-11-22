package com.shente.cams.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TResult {

    private Integer rId;
    private Integer courseId;
    private String account;
    private String resultData;
    private Character resultType;
}
