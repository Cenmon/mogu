package com.cenmo.mogu.common.ex;

import com.cenmo.mogu.common.constant.ResultEnum;
import com.cenmo.mogu.common.vo.MoguResult;

/**
 * @author Cenmo
 * @Date 2020-10-16 2020/10/16
 */
public class MoguException extends RuntimeException{
    private ResultEnum resEnum;

    public MoguException(){}

    public MoguException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.resEnum = resultEnum;
    }

    public ResultEnum getResEnum() {
        return resEnum;
    }

    public void setResEnum(ResultEnum resEnum) {
        this.resEnum = resEnum;
    }

    @Override
    public String toString() {
        return "MoguException{" +
                "resEnum=" + resEnum +
                '}';
    }
}
