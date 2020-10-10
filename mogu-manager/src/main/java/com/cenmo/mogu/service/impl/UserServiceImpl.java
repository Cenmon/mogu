package com.cenmo.mogu.service.impl;

import com.cenmo.mogu.pojo.User;
import com.cenmo.mogu.mapper.UserMapper;
import com.cenmo.mogu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
