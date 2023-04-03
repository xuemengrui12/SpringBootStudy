package com.xmr.vo;

import com.google.gson.Gson;
import com.xmr.common.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xmr on 2020/3/25.
 */
public class ResultVO {

    private static final Logger logger = LoggerFactory.getLogger(ResultVO.class);

    public static final ResultVO SUCCESS = new ResultVO(ResultCode.SUCCESS);
    public static final ResultVO FAIL = new ResultVO(ResultCode.FAIL);
    public static final ResultVO FORBIDDEN = new ResultVO(ResultCode.FORBIDDEN);
    public static final ResultVO NULL = new ResultVO(ResultCode.NULL);
    public static final ResultVO EXCEPTION = new ResultVO(ResultCode.EXCEPTION);
    public static final ResultVO PARAM_INVALID = new ResultVO(ResultCode.PARAM_INVALID);

    /**
     * 返回代码
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 默认构造，返回操作正确的返回代码和信息
     */
    public ResultVO() {
        this.setCode(ResultCode.SUCCESS.code());
        this.setMessage(ResultCode.SUCCESS.msg());
    }

    /**
     * 构造一个返回特定代码的ResultVO对象
     *
     * @param code
     */
    public ResultVO(ResultCode code) {
        this.setCode(code.code());
        this.setMessage(code.msg());
    }

    public ResultVO(String code, String message) {
        super();
        this.setCode(code);
        this.setMessage(message);
    }

    /**
     * 默认值返回，默认返回正确的code和message
     *
     * @param data
     */
    public ResultVO(Object data) {
        ResultCode rc = data == null ? ResultCode.NULL : ResultCode.SUCCESS;
        this.setCode(rc.code());
        this.setMessage(rc.msg());
        this.setData(data);
    }

    /**
     * 构造返回代码，以及自定义的错误信息
     *
     * @param code
     * @param message
     */
    public ResultVO(ResultCode code, String message) {
        this.setCode(code.code());
        this.setMessage(message);
    }

    /**
     * 构造自定义的code，message，以及data
     *
     * @param code
     * @param message
     * @param data
     */
    public ResultVO(ResultCode code, String message, Object data) {
        this.setCode(code.code());
        this.setMessage(message);
        this.setData(data);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        // request请求响应的时候，一定会走到这里，判断如果code不是成功状态，就输出日志
        if (!ResultCode.SUCCESS.code().equals(code)) {
            logger.info("ResultVO={}", new Gson().toJson(this));
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
