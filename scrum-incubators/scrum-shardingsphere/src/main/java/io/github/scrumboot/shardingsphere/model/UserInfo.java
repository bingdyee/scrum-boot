package io.github.scrumboot.shardingsphere.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Bing D. Yee
 * @since 2022/04/29
 */
@Data
@TableName("user_info")
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
