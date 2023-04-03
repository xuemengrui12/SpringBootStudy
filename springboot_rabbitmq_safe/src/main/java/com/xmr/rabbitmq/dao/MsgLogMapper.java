package com.xmr.rabbitmq.dao;

import com.xmr.rabbitmq.pojo.MsgLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 消息投递日志(MsgLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-03 23:11:02
 */
public interface MsgLogMapper {


    void updateStatus(MsgLog msgLog);

    List<MsgLog> selectTimeoutMsg();

    void updateTryCount(MsgLog msgLog);

    MsgLog selectByPrimaryKey(String msgId);

    /**
     * 通过ID查询单条数据
     *
     * @param msgId 主键
     * @return 实例对象
     */
    MsgLog queryById(String msgId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<MsgLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param msgLog 实例对象
     * @return 对象列表
     */
    List<MsgLog> queryAll(MsgLog msgLog);

    /**
     * 新增数据
     *
     * @param msgLog 实例对象
     * @return 影响行数
     */
    int insert(MsgLog msgLog);

    /**
     * 修改数据
     *
     * @param msgLog 实例对象
     * @return 影响行数
     */
    int update(MsgLog msgLog);

    /**
     * 通过主键删除数据
     *
     * @param msgId 主键
     * @return 影响行数
     */
    int deleteById(String msgId);

}