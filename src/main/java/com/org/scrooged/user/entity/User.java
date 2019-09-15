package com.org.scrooged.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>Title: User</p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai oneself 2019</p>
 *
 * @author luyangqian
 * version 1.0
 * <pre>History
 *          2019-09-12   luyangqian  Created
 * </pre>
 */
@TableName(value = "t_user")
public class User extends Model<User> implements Serializable {

    @TableId
    private String uId;

    private String username;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    protected Serializable pkVal() {
        return uId;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId='" + uId + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
