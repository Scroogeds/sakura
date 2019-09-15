package com.org.scrooged.user.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.org.scrooged.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>Title: UserDao</p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai oneself 2019</p>
 *
 * @author luyangqian
 * version 1.0
 * <pre>History
 *          2019-09-12   luyangqian  Created
 * </pre>
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}
