package com.xmr.rabbitmq.pojo;

import com.xmr.rabbitmq.common.Constant;
import com.xmr.rabbitmq.util.JodaTimeUtil;
import com.xmr.rabbitmq.util.JsonUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息投递日志(MsgLog)实体类
 *
 * @author makejava
 * @since 2020-03-03 23:10:52
 */
@Data
public class MsgLog implements Serializable {
    private static final long serialVersionUID = 620110520235371777L;
    
    private String msgId;
    
    private String msg;
    
    private String exchange;
    
    private String routingKey;
    
    private Integer status;
    
    private Integer tryCount;
    
    private Date nextTryTime;
    
    private Date createTime;
    
    private Date updateTime;

    public MsgLog() {
    }

    public MsgLog(String msgId, Object msg, String exchange, String routingKey) {
        this.msgId = msgId;
        this.msg = JsonUtil.objToStr(msg);
        this.exchange = exchange;
        this.routingKey = routingKey;

        this.status = Constant.MsgLogStatus.DELIVERING;
        this.tryCount = 0;

        Date date = new Date();
        this.createTime = date;
        this.updateTime = date;
        this.nextTryTime = (JodaTimeUtil.plusMinutes(date, 1));
    }
}