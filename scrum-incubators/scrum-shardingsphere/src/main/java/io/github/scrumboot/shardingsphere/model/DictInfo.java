package io.github.scrumboot.shardingsphere.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.scrumboot.langs.SnowflakeWorker;
import io.github.scrumboot.shardingsphere.mapper.DictInfoMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Bing D. Yee
 * @since 2022/04/30
 */
@Data
@NoArgsConstructor
@TableName("dict_info")
public class DictInfo {

    private Long id;

    private String dictName;

    private String dictCode;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public DictInfo(String name, String code) {
        this.id = SnowflakeWorker.nextId();
        this.dictName = name;
        this.dictCode = code;
        this.createTime = LocalDateTime.now();
        this.updateTime = this.createTime;
    }

}
