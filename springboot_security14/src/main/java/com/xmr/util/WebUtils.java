package com.xmr.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Administrator
 * @Date: 17/8/2023
 * @LastEditTime: 17/8/2023 下午 9:12
 * @LastEditors: Administrator
 * @Description:
 */
public class WebUtils {
    /**
     * 在 HttpServletResponse 中渲染字符串的方法。
     * 它将给定的字符串写入 HttpServletResponse 的输出流，
     * 并设置相应的响应状态码、内容类型和字符编码。
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(string);
            response.getWriter().flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
