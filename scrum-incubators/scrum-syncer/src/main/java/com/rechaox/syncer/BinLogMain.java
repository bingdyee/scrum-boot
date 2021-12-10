package com.rechaox.syncer;

import com.github.shyiko.mysql.binlog.BinaryLogClient;

/**
 * @author Bing D. Yee
 * @since 2021/12/09
 */
public class BinLogMain {

    public static void main(String[] args) {
        BinaryLogClient client = new BinaryLogClient("192.168.17.110", 3306, "root", "sdzy@mysql");

    }

}
