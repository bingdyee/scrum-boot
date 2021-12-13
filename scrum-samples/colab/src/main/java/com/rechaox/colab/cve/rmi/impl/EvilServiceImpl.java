package com.rechaox.colab.cve.rmi.impl;

import com.rechaox.colab.cve.rmi.EvilService;

/**
 * @author Bing D. Yee
 * @since 2021/12/11
 */
public class EvilServiceImpl implements EvilService {

    static {
        System.err.println("Test Log4j CVE.");
    }

    @Override
    public void hello() {
        System.err.println("Hello Apache Log4j.");
    }

}
