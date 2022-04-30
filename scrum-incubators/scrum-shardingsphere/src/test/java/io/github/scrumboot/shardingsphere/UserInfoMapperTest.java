package io.github.scrumboot.shardingsphere;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.scrumboot.langs.SnowflakeWorker;
import io.github.scrumboot.langs.constant.Constant;
import io.github.scrumboot.shardingsphere.mapper.DictInfoMapper;
import io.github.scrumboot.shardingsphere.mapper.EnumInfoMapper;
import io.github.scrumboot.shardingsphere.mapper.UserInfoMapper;
import io.github.scrumboot.shardingsphere.model.DictInfo;
import io.github.scrumboot.shardingsphere.model.EnumInfo;
import io.github.scrumboot.shardingsphere.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * @author Bing D. Yee
 * @since 2022/04/29
 */
@SpringBootTest
public class UserInfoMapperTest {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private DictInfoMapper dictInfoMapper;
    @Autowired
    private EnumInfoMapper enumInfoMapper;


    @Test
    public void testInsertDict() {
        DictInfo dictInfo = new DictInfo("性别", "GENDER");
        dictInfoMapper.insert(dictInfo);

        EnumInfo unknown = new EnumInfo(dictInfo.getId(), "未知", "UNKNOWN", 0);
        enumInfoMapper.insert(unknown);

        EnumInfo male = new EnumInfo(dictInfo.getId(), "男", "MALE", 1);
        enumInfoMapper.insert(male);

        EnumInfo female = new EnumInfo(dictInfo.getId(), "女", "FEMALE", 2);
        enumInfoMapper.insert(female);
    }

    @Test
    public void testDeleteDict() {
        dictInfoMapper.delete(Wrappers.emptyWrapper());
        enumInfoMapper.delete(Wrappers.emptyWrapper());
    }

    @Test
    public void testInsert() {
        final Random random = new Random();
        for (int i = 0; i < 20; ++i) {
            final long id = random.nextLong();
            UserInfo userInfo = new UserInfo();
            userInfo.setId(Math.abs(id));
            userInfo.setUsername("Bing D. Yee");
            userInfo.setAge(18);
            userInfo.setAddress("中国浙江");
            userInfo.setGender(Constant.MALE);
            userInfo.setBirthDate(LocalDate.of(1994, 10, 23));
            userInfo.setCreateTime(LocalDateTime.now());
            userInfo.setUpdateTime(LocalDateTime.now());
            userInfoMapper.insert(userInfo);
        }
    }

    @Test
    public void testDelete() {
        userInfoMapper.delete(Wrappers.emptyWrapper());
    }

    @Test
    public void testQuery() {
        final List<UserInfo> userInfos = userInfoMapper.selectBatchIds(List.of(1L, 2L, 3L, 4L));
        System.err.println(userInfos);
    }

}
