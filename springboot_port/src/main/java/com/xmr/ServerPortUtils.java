package com.xmr;

import java.util.Random;

/**
 * @Author: Administrator
 * @Date: 2022/4/19
 * @LastEditTime: 2022/4/19 11:03
 * @LastEditors: Administrator
 * @Description:
 */
public class ServerPortUtils {
    public static int getAvailablePort(){
        int max=65535;
        int min=2000;
        Random random=new Random();
        int port=random.nextInt(max)%(max-min+1)+min;
        boolean isUsed=NetUtils.isLocalPortUsing(port);
        if (isUsed){
            return getAvailablePort();
        }
        return port;
    }
}
