package com.xmr.rabbitmq.service;

import com.xmr.rabbitmq.pojo.MsgLog;

import java.util.Date;
import java.util.List;

/**
 * 消息投递日志(MsgLog)表服务接口
 *
 * @author makejava
 * @since 2020-03-03 23:11:02
 */
public interface IMsgLogService {


    void updateStatus(String msgId, Integer status);

    MsgLog selectByMsgId(String msgId);

    List<MsgLog> selectTimeoutMsg();

    void updateTryCount(String msgId, Date tryTime);

    /**
     * 通过ID查询单条数据
     *
     * @param msgId 主键
     * @return 实例对象
     */
    MsgLog queryById(String msgId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<MsgLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param msgLog 实例对象
     * @return 实例对象
     */
    MsgLog insert(MsgLog msgLog);

    /**
     * 修改数据
     *
     * @param msgLog 实例对象
     * @return 实例对象
     */
    MsgLog update(MsgLog msgLog);

    /**
     * 通过主键删除数据
     *
     * @param msgId 主键
     * @return 是否成功
     */
    boolean deleteById(String msgId);

}