package com.xmr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmr.entity.User;
import com.xmr.mapper.UserMapper;
import com.xmr.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xmr
 * @since 2023-12-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
