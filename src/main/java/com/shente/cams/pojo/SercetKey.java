package com.shente.cams.pojo;

import lombok.*;

/**
 * @author sugar
 * 2019/11/22
 * 下午3:23
 * 课程对应的秘钥
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SercetKey {
    private Integer kId;
    private Integer userId;
    private Integer courseId;
    private String text;
}
