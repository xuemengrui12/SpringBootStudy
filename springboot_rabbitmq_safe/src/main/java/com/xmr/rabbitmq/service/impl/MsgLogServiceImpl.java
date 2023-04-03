package com.xmr.rabbitmq.service.impl;

import com.xmr.rabbitmq.dao.MsgLogMapper;
import com.xmr.rabbitmq.pojo.MsgLog;
import com.xmr.rabbitmq.service.IMsgLogService;
import com.xmr.rabbitmq.util.JodaTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 消息投递日志(MsgLog)表服务实现类
 *
 * @author makejava
 * @since 2020-03-03 23:11:02
 */
@Service("msgLogService")
public class MsgLogServiceImpl implements IMsgLogService {
    @Resource
    private MsgLogMapper msgLogMapper;


    @Override
    public void updateStatus(String msgId, Integer status) {
        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setStatus(status);
        msgLog.setUpdateTime(new Date());
        msgLogMapper.updateStatus(msgLog);
    }
    @Override
    public MsgLog selectByMsgId(String msgId) {
        return msgLogMapper.selectByPrimaryKey(msgId);
    }

    @Override
    public List<MsgLog> selectTimeoutMsg() {
        return msgLogMapper.selectTimeoutMsg();
    }

    @Override
    public void updateTryCount(String msgId, Date tryTime) {
        Date nextTryTime = JodaTimeUtil.plusMinutes(tryTime, 1);

        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setNextTryTime(nextTryTime);

        msgLogMapper.updateTryCount(msgLog);
    }
    /**
     * 通过ID查询单条数据
     *
     * @param msgId 主键
     * @return 实例对象
     */
    @Override
    public MsgLog queryById(String msgId) {
        return this.msgLogMapper.queryById(msgId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<MsgLog> queryAllByLimit(int offset, int limit) {
        return this.msgLogMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param msgLog 实例对象
     * @return 实例对象
     */
    @Override
    public MsgLog insert(MsgLog msgLog) {
        this.msgLogMapper.insert(msgLog);
        return msgLog;
    }

    /**
     * 修改数据
     *
     * @param msgLog 实例对象
     * @return 实例对象
     */
    @Override
    public MsgLog update(MsgLog msgLog) {
        this.msgLogMapper.update(msgLog);
        return this.queryById(msgLog.getMsgId());
    }

    /**
     * 通过主键删除数据
     *
     * @param msgId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String msgId) {
        return this.msgLogMapper.deleteById(msgId) > 0;
    }
}