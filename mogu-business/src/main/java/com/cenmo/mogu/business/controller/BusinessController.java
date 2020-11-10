package com.cenmo.mogu.business.controller;


import com.cenmo.mogu.business.service.BusinessService;
import com.cenmo.mogu.common.constant.ResultEnum;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 商家表 前端控制器
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    /**
     * 根据类型验证数据是否存在
     * @param param 1：店主名 2：电子邮箱 3：手机号
     * @param type
     * @return
     */
    @GetMapping("/check/{param}/{type}")
    public MoguResult checkBusinessData(@PathVariable String param, @PathVariable Integer type) {
        try {
//            System.out.println("type:"+type);
//            System.out.println("param:"+param);
//            param = new String(param.getBytes("ISO8859-1"),"utf-8");
            return businessService.checkBusinessData(param, type);
        }catch (Exception e) {
            e.printStackTrace();
            return MoguResult.error(ResultEnum.PARAM_ERROR);
        }
    }

    /**
     * 修改店主信息
     */
    @PostMapping(value = "/update/data")
    public MoguResult updateBusiness(Business Business) {
        boolean res = businessService.updateById(Business);
        return MoguResult.ok();
    }

    /**
     * 修改店主密码
     * @param request
     * @param response
     * @param model
     * @return
     */
    @PostMapping(value = "/update")
    public MoguResult updateBusinessPassword(String old, String news, @CookieValue() HttpServletRequest request,
                                          HttpServletResponse response, Model model) {
        try {
            //修改密码
            MoguResult result = businessService.updateBusinessPassword(old,news,request,response);
            if( result.getSuccess() ) {
                return MoguResult.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MoguResult.build(false, 500,"修改密码失败！",null);
    }

    /**
     * 店主退出
     * @return
     */
    @GetMapping(value = "/quit")
    public MoguResult quitBusiness(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            // 一台电脑只能有一个token，即一个店主登录
            MoguResult result = businessService.deleteBusiness(request,response);
            if( result.getSuccess() ) {
                return MoguResult.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MoguResult.build(false,500, "退出失败",null);

    }


    /**
     * 店主登录
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/login")
    public MoguResult BusinessLogin(String username, String password,
                                 HttpServletRequest request, HttpServletResponse response) {
        try {
			System.out.println(username);
			System.out.println(password);
            MoguResult result = businessService.login(username, password, request, response);
            System.out.println(result);
            if(result.getSuccess()){
                response.sendRedirect("/");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MoguResult.build(false,500, "系统错误",null);
    }
}

