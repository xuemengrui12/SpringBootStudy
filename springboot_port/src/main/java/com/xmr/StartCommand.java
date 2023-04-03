package com.xmr;

import org.springframework.util.StringUtils;


/**
 * @Author: Administrator
 * @Date: 2022/4/19
 * @LastEditTime: 2022/4/19 10:56
 * @LastEditors: Administrator
 * @Description: 未完
 */
public class StartCommand {
    //    private Logger logger= LoggerFactory.getLogger(StartCommand.class);
    public StartCommand(String[] args) {
        boolean isServerPort = false;
        String serverPort = "";
        if (args != null) {
            for (String arg : args) {
                if (StringUtils.hasText(arg) && arg.startsWith("--server.port")) {
                    isServerPort = true;
                    serverPort = arg;
                    break;
                }
            }
        }
        if (!isServerPort) {
            int port = ServerPortUtils.getAvailablePort();
        }
    }
}
