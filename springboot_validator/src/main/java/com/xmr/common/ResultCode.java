package com.xmr.common;

/**
 * Created by xmr on 2020/3/25.
 */
public enum ResultCode {

    /**
     * 操作成功
     */
    SUCCESS("1", "成功"),

    /**
     * 操作失败
     */
    FAIL("0", "失败"),

    /**
     * 操作失败
     */
    NULL("-1", "数据不存在"),

    /**
     * 系统发生异常
     */
    EXCEPTION("-2", "系统异常"),

    /**
     * 没有权限
     */
    FORBIDDEN("9403", "没有权限"),

    /**
     * 参数错误
     */
    PARAM_INVALID("-3", "参数错误");

    private String code;
    private String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String code() {
        return code;
    }

    public String msg() {
        return msg;
    }

}