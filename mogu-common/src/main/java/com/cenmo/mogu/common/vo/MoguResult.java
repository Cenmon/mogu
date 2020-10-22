package com.cenmo.mogu.common.vo;

import com.cenmo.mogu.common.constant.ResultEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cenmo
 * @Date 2020-10-16 2020/10/16
 */
public class MoguResult implements Serializable {

    private Boolean success;
    private Integer status;
    private String msg;
    private Map<String,Object> data;

    private MoguResult(){}

    private MoguResult(Boolean success,Integer status, String msg, Map<String, Object> data) {
        this.success = success;
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 未加参数的成功返回，默认状态码为200，无返回对象
     * @return
     */
    public static MoguResult ok(){
        return new MoguResult(ResultEnum.SUCCESS.getSuccess(),
                ResultEnum.SUCCESS.getStatus(),
                ResultEnum.SUCCESS.getMsg(),
                new HashMap<>());
    }

    /**
     * 带返回对象的成功返回，状态码为200，有返回对象
     * @param map
     * @return
     */
    public static MoguResult ok(Map<String,Object> map){
        return new MoguResult(ResultEnum.SUCCESS.getSuccess(),
                ResultEnum.SUCCESS.getStatus(),
                ResultEnum.SUCCESS.getMsg(),
                map);
    }

    /**
     * 带返回对象的成功返回，状态码为200，有返回对象
     * @param key 指定返回对象的key值
     * @param data 指定返回的结果对象
     * @return
     */
    public static MoguResult ok(String key, Object data){
        HashMap<String, Object> map = new HashMap<>();
        map.put(key, data);
        return new MoguResult(ResultEnum.SUCCESS.getSuccess(),
                ResultEnum.SUCCESS.getStatus(),
                ResultEnum.SUCCESS.getMsg(),
                map);
    }

    /**
     * 无参数的错误返回，状态码为500，默认错误类型为未知错误
     * @return
     */
    public static MoguResult error(){
        return new MoguResult(ResultEnum.UNKNOWN_REASON.getSuccess(),
                ResultEnum.UNKNOWN_REASON.getStatus(),
                ResultEnum.UNKNOWN_REASON.getMsg(),
                null);
    }

    /**
     * 指定错误类型的错误返回，如sql错误，json格式错误，参数错误，文件上传错误
     * @param resultEnum
     * @return
     */
    public static MoguResult error(ResultEnum resultEnum){
        return new MoguResult(resultEnum.getSuccess(),
                resultEnum.getStatus(),
                resultEnum.getMsg(),
                null);
    }

    /**
     * 构建自己的返回结果
     * @param success 是否成功返回
     * @param status 状态码
     * @param msg 返回消息
     * @param data 返回对象
     * @return
     */
    public static MoguResult build(Boolean success, Integer status, String msg, Map<String, Object> data){
        return new MoguResult(success,status,msg,data);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
