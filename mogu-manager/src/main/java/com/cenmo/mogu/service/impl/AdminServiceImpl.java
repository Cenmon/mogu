package com.cenmo.mogu.service.impl;

import com.cenmo.mogu.pojo.Admin;
import com.cenmo.mogu.mapper.AdminMapper;
import com.cenmo.mogu.service.AdminService;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
