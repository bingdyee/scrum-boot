package io.github.scrumboot.shardingsphere.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Bing D. Yee
 * @since 2022/04/29
 */
@Data
public class UserInfo {

    private Long id;

    private String username;

    private Integer age;

    private Integer gender;

    private LocalDate birthDate;

    private String address;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
