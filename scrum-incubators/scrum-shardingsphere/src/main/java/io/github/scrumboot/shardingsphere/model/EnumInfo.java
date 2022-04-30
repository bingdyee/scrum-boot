package io.github.scrumboot.shardingsphere.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.scrumboot.langs.SnowflakeWorker;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Bing D. Yee
 * @since 2022/04/30
 */
@Data
@NoArgsConstructor
@TableName("enum_info")
public class EnumInfo {

    private Long id;

    private Long dictId;

    private String enumName;

    private String enumCode;

    private Integer enumValue;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public EnumInfo(Long dictId, String name, String code, Integer value) {
        this.id = SnowflakeWorker.nextId();
        this.dictId = dictId;
        this.enumName = name;
        this.enumCode = code;
        this.enumValue = value;
        this.createTime = LocalDateTime.now();
        this.updateTime = this.createTime;
    }

}
