package com.cenmo.mogu.manager.service;

import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
public interface AdminService extends IService<Admin> {

    MoguResult checkAdminData(String param, Integer type);

    MoguResult login(String username, String password, HttpServletRequest request, HttpServletResponse response);

    Admin getAdminByToken(String token);

    MoguResult deleteAdmin(HttpServletRequest request,HttpServletResponse response);

    MoguResult updateAdminPassword(String old,String news,HttpServletRequest request,HttpServletResponse response);

}
