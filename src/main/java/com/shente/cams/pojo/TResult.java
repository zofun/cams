package com.shente.cams.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TResult {

    @JsonProperty("rId")
    private Integer rId;

    private Integer courseId;
    private String account;
    private String resultData;
    private Character resultType;
}
