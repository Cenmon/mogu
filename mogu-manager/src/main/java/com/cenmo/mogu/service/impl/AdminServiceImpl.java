package com.cenmo.mogu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cenmo.mogu.common.constant.ResultEnum;
import com.cenmo.mogu.common.utils.CookieUtils;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.dao.JedisClient;
import com.cenmo.mogu.pojo.Admin;
import com.cenmo.mogu.mapper.AdminMapper;
import com.cenmo.mogu.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jayway.jsonpath.Criteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-09
 */
@Service
@ConfigurationProperties("mogu.redis.admin")
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private JedisClient jedisClient;

    private String SESSION_KEY;

    private Integer SESSION_EXPIRE_TIME;
    /**
     * 根据类型验证数据
     * 1：用户名 2：电子邮箱 3：手机号
     */
    @Override
    public MoguResult checkAdminData(String param, Integer type) {
        HashMap<String, Object> map = new HashMap<>();

        //验证数据类型：1：用户名 2：电子邮箱 3：手机号
        if(type == 1) {
            map.put("username",param);
        }else if (type == 2) {
            map.put("email",param);
        }else if(type == 3){
            map.put("phone",param);
        }else {
            return MoguResult.error(ResultEnum.PARAM_ERROR);
        }

        List<Admin> list = baseMapper.selectByMap(map);
        if(list!=null && list.size()>0) {
            return MoguResult.error(ResultEnum.PARAM_ERROR);
        }
        return MoguResult.ok();
    }

    /**
     * 修改管理员密码
     */
    @Override
    public MoguResult updateAdminPassword(String old,String news,HttpServletRequest request,
                                                HttpServletResponse response) {

        //获取redis中的管理员信息
        String token = CookieUtils.getCookieValue(request, "MG_ADMIN");
        String json = jedisClient.get(SESSION_KEY+":"+token);
        if( StringUtils.isBlank(json) ) {
            return MoguResult.build(false,500, "登录时间超长，请重新登录!",null);
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        //检验原密码
        Admin admin = baseMapper.selectById((Serializable) jsonObject.get("id"));
        if(!DigestUtils.md5DigestAsHex(old.getBytes()).equals(admin.getPassword())) {
            return MoguResult.build(false,500, "修改密码错误!",null);
        }
        //密码加密
        admin.setPassword(DigestUtils.md5DigestAsHex(news.getBytes()));

        //修改密码
        baseMapper.updateById(admin);
//        adminMapper.updateByPrimaryKeySelective(Admin);
        //删除cookie
        CookieUtils.deleteCookie(request, response, "MG_ADMIN");
        jedisClient.del(SESSION_KEY+":"+token);
        return MoguResult.ok();
    }



    /**
     * 管理员登录 
     */
    @Override
    public MoguResult login(String username, String password,
                                  HttpServletRequest request,HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",username);
        List<Admin> list = baseMapper.selectByMap(map);

        //查找用户名
        if(list==null || list.size()==0) {
            return MoguResult.build(false, 400,"用户名或密码错误",null);
        }

        //查找密码
        Admin admin = list.get(0);
        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(admin.getPassword())) {
            return MoguResult.build(false, 400,"用户名或密码错误",null);
        }
        //生成token：redis缓存中用于索引用户信息的key
        String token= UUID.randomUUID().toString();
        String jedisKey=SESSION_KEY+":"+token;

        //将token存入redis并设置过期时间
        //存入之前，将user对象的密码清空，防止信息泄露
        admin.setPassword(null);
        jedisClient.set(jedisKey, JSONObject.toJSONString(admin));
        jedisClient.expire(jedisKey, SESSION_EXPIRE_TIME);
        //将token存入cookie：一台电脑只能登录一个 
//		jedisClient.set(REDIS_ADMIN_TOKEN,token);
        CookieUtils.setCookie(request, response, "MG_ADMIN", token);
        return MoguResult.ok("token",token);
    }

    /**
     * 根据所给token获取用户信息
     */
    @Override
    public Admin getAdminByToken(String token) {

        String jedisKey=SESSION_KEY+":"+token;
        String json = jedisClient.get(jedisKey);
        if(StringUtils.isBlank(json)) {
            return null;
        }

        //重新设置过期时间，一小时
        jedisClient.expire(jedisKey, SESSION_EXPIRE_TIME);

        return JSONObject.parseObject(json,Admin.class);
    }

    /**
     * 管理员退出：根据token删除redis中的用户session信息
     */
    @Override
    public MoguResult deleteAdmin(HttpServletRequest request,HttpServletResponse response) {
        String token = CookieUtils.getCookieValue(request, "MG_ADMIN");
        String jedisKey = SESSION_KEY + ":" +token;
        jedisClient.del(jedisKey);
        CookieUtils.deleteCookie(request, response, "MG_ADMIN");
        return MoguResult.ok();
    }

}
