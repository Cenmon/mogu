package com.cenmo.mogu.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.Business;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 商家表 服务类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-30
 */
public interface BusinessService extends IService<Business> {

    MoguResult checkBusinessData(String param, Integer type);

    MoguResult login(String username, String password, HttpServletRequest request, HttpServletResponse response);

    Business getBusinessByToken(String token);

    MoguResult deleteBusiness(HttpServletRequest request,HttpServletResponse response);

    MoguResult updateBusinessPassword(String old,String news,HttpServletRequest request,HttpServletResponse response);
}
