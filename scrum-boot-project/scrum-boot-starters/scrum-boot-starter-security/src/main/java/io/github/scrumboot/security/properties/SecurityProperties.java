package io.github.scrumboot.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bingdyee
 * @since 2022/03/02
 */
@Configuration
@ConfigurationProperties(prefix = "scrum.security")
public class SecurityProperties {

    private int tokenExpireIn = 7200;

    private List<String> ignoreList = new ArrayList<>();

    public int getTokenExpireIn() {
        return tokenExpireIn;
    }

    public void setTokenExpireIn(int tokenExpireIn) {
        this.tokenExpireIn = tokenExpireIn;
    }

    public List<String> getIgnoreList() {
        return ignoreList;
    }

    public void setIgnoreList(List<String> ignoreList) {
        this.ignoreList = ignoreList;
    }
}
