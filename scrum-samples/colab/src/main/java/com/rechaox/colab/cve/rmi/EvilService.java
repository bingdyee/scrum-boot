package com.rechaox.colab.cve.rmi;

import java.io.Serializable;
import java.rmi.Remote;

/**
 * @author Bing D. Yee
 * @since 2021/12/11
 */
public interface EvilService extends Remote, Serializable {

    void hello();


}
