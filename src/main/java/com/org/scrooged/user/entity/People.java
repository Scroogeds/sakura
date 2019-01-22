package com.org.scrooged.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @author Design By Scrooged
 * @version 1.0
 * @description
 * @date 2019/1/16 15:13
 */
@TableName(value = "USERS.t_people")
public class People extends Model<People> implements Serializable {

    @TableId
    private String pId;

    private String name;

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Serializable pkVal() {
        return pId;
    }

    @Override
    public String toString() {
        return "People{" +
                "pId='" + pId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
