package com.cenmo.mogu.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cenmo.mogu.business.service.AdminService;
import com.cenmo.mogu.mapper.AdminMapper;
import com.cenmo.mogu.pojo.Admin;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-30
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
