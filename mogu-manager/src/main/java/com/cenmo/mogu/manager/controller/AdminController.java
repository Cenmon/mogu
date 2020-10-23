package com.cenmo.mogu.manager.controller;


import com.cenmo.mogu.common.constant.ResultEnum;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.pojo.Admin;
import com.cenmo.mogu.manager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/list")
    public List<Admin> getList(){
        List<Admin> list = adminService.list(null);
//        return JSON.toJSONString(list);
        return list;
    }

    /**
     * 根据类型验证数据是否存在
     * @param param 1：用户名 2：电子邮箱 3：手机号
     * @param type
     * @return
     */
    @GetMapping("/check/{param}/{type}")
    public MoguResult checkAdminData(@PathVariable String param, @PathVariable Integer type) {
        try {
            param = new String(param.getBytes("ISO8859-1"),"utf-8");
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return MoguResult.error(ResultEnum.PARAM_ERROR);
        }
        return adminService.checkAdminData(param, type);
    }

    /**
     * 修改管理员信息
     */
    @PostMapping(value = "/update/data")
    public MoguResult updateAdmin(Admin admin) {
        boolean res = adminService.updateById(admin);
        return MoguResult.ok();
    }

    /**
     * 修改管理员密码
     * @param request
     * @param response
     * @param model
     * @return
     */
    @PostMapping(value = "/update")
    public MoguResult updateAdminPassword(String old, String news, @CookieValue() HttpServletRequest request,
                                          HttpServletResponse response, Model model) {
        try {
            //修改密码
            MoguResult result = adminService.updateAdminPassword(old,news,request,response);
            if( result.getSuccess() ) {
                return MoguResult.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MoguResult.build(false, 500,"修改密码失败！",null);
    }

    /**
     * 管理员退出
     * @return
     */
    @GetMapping(value = "/quit")
    public MoguResult quitAdmin(HttpServletRequest request,HttpServletResponse response,Model model) {
        try {
            // 一台电脑只能有一个token，即一个管理员登录
            MoguResult result = adminService.deleteAdmin(request,response);
            if( result.getSuccess() ) {
                return MoguResult.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MoguResult.build(false,500, "退出失败",null);

    }


    /**
     * 管理员登录
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/login")
    public MoguResult adminLogin(String username, String password,
                                       HttpServletRequest request, HttpServletResponse response) {
        try {
//			System.out.println(username);
//			System.out.println(password);
            MoguResult result = adminService.login(username, password, request, response);
            if (result.getSuccess()){
                return MoguResult.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MoguResult.build(false,500, "系统错误",null);
    }

}

