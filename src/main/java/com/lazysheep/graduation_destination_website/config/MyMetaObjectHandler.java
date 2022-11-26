package com.lazysheep.graduation_destination_website.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        this.setFieldValByName("createTime",new Date(),metaObject);
        this.strictInsertFill(metaObject,"createTime",Long.class,System.currentTimeMillis());
        this.strictInsertFill(metaObject,"updateTime",Long.class,System.currentTimeMillis());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        this.setFieldValByName("updateTime",new Date(),metaObject);

        this.strictUpdateFill(metaObject,"updateTime",Long.class,System.currentTimeMillis());
    }


}
