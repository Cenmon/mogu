package com.cenmo.mogu.sso.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 填充birth字段的值，自动填充即执行sql语句之前，对pojo进行二次赋值，将此处的birth赋值进pojo
        //  注：使用前，需把该对象注入IOC容器
        this.setFieldValByName("updated", new Date(), metaObject);
        this.setFieldValByName("created", new Date(), metaObject);
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updated", new Date(), metaObject);
    }
}
