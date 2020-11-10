package com.cenmo.mogu.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cenmo.mogu.business.dao.JedisClient;
import com.cenmo.mogu.business.service.BusinessService;
import com.cenmo.mogu.common.constant.ResultEnum;
import com.cenmo.mogu.common.utils.CookieUtils;
import com.cenmo.mogu.common.vo.MoguResult;
import com.cenmo.mogu.mapper.BusinessMapper;
import com.cenmo.mogu.pojo.Business;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 商家表 服务实现类
 * </p>
 *
 * @author Cenmo
 * @since 2020-10-30
 */
@Service
@ConfigurationProperties("mogu.redis.business")
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {

    @Autowired
    private JedisClient jedisClient;

    private String sessionKey;

    private Integer sessionExpireTime;
    /**
     * 根据类型验证数据
     * 1：用户名 2：电子邮箱 3：手机号
     */
    @Override
    public MoguResult checkBusinessData(String param, Integer type) {
        HashMap<String, Object> map = new HashMap<>();

        //验证数据类型：1：用户名 2：电子邮箱 3：手机号
        if(type == 1) {
            map.put("host_name",param);
        }else if (type == 2) {
            map.put("email",param);
        }else if(type == 3){
            map.put("phone",param);
        }else {
            return MoguResult.error(ResultEnum.PARAM_ERROR);
        }

        List<Business> list = baseMapper.selectByMap(map);
        if(list!=null && list.size()>0) {
            return MoguResult.ok();
        }
        return MoguResult.error(ResultEnum.PARAM_ERROR);
    }

    /**
     * 修改管理员密码
     */
    @Override
    public MoguResult updateBusinessPassword(String old,String news,HttpServletRequest request,
                                          HttpServletResponse response) {

        //获取redis中的管理员信息
        String token = CookieUtils.getCookieValue(request, "MG_BUSINESS");
        String json = jedisClient.get(sessionKey+":"+token);
        if( StringUtils.isBlank(json) ) {
            return MoguResult.build(false,500, "登录时间超长，请重新登录!",null);
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        //检验原密码
        Business admin = baseMapper.selectById((Serializable) jsonObject.get("id"));
        if(!DigestUtils.md5DigestAsHex(old.getBytes()).equals(admin.getPassword())) {
            return MoguResult.build(false,500, "修改密码错误!",null);
        }
        //密码加密
        admin.setPassword(DigestUtils.md5DigestAsHex(news.getBytes()));

        //修改密码
        baseMapper.updateById(admin);
//        adminMapper.updateByPrimaryKeySelective(Business);
        //删除cookie
        CookieUtils.deleteCookie(request, response, "MG_BUSINESS");
        jedisClient.del(sessionKey+":"+token);
        return MoguResult.ok();
    }



    /**
     * 管理员登录 
     */
    @Override
    public MoguResult login(String username, String password,
                            HttpServletRequest request,HttpServletResponse response) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("host_name",username);
        List<Business> list = baseMapper.selectByMap(map);

        //查找用户名
        if(list==null || list.size()==0) {
            return MoguResult.build(false, 400,"用户名或密码错误",null);
        }

        //查找密码
        Business admin = list.get(0);
        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(admin.getPassword())) {
            return MoguResult.build(false, 400,"用户名或密码错误",null);
        }
        //生成token：redis缓存中用于索引用户信息的key
        String token= UUID.randomUUID().toString();
        String jedisKey=sessionKey+":"+token;

        //将token存入redis并设置过期时间
        //存入之前，将user对象的密码清空，防止信息泄露
        admin.setPassword(null);
        jedisClient.set(jedisKey, JSONObject.toJSONString(admin));
        jedisClient.expire(jedisKey, sessionExpireTime);
        //将token存入cookie：一台电脑只能登录一个 
//		jedisClient.set(REDIS_ADMIN_TOKEN,token);
        CookieUtils.setCookie(request, response, "MG_BUSINESS", token);
        return MoguResult.ok("token",token);
    }

    /**
     * 根据所给token获取用户信息
     */
    @Override
    public Business getBusinessByToken(String token) {

        String jedisKey=sessionKey+":"+token;
        String json = jedisClient.get(jedisKey);
        if(StringUtils.isBlank(json)) {
            return null;
        }

        //重新设置过期时间，一小时
        jedisClient.expire(jedisKey, sessionExpireTime);

        return JSONObject.parseObject(json,Business.class);
    }

    /**
     * 管理员退出：根据token删除redis中的用户session信息
     */
    @Override
    public MoguResult deleteBusiness(HttpServletRequest request,HttpServletResponse response) {
        String token = CookieUtils.getCookieValue(request, "MG_BUSINESS");
        String jedisKey = sessionKey + ":" +token;
        jedisClient.del(jedisKey);
        CookieUtils.deleteCookie(request, response, "MG_BUSINESS");
        return MoguResult.ok();
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Integer getSessionExpireTime() {
        return sessionExpireTime;
    }

    public void setSessionExpireTime(Integer sessionExpireTime) {
        this.sessionExpireTime = sessionExpireTime;
    }
}
