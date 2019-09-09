package com.org.scrooged.user.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.org.scrooged.user.entity.People;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Design By Scrooged
 * @version 1.0
 * @description
 * @date 2019/1/16 15:19
 */
@Mapper
public interface PeopleDao extends BaseMapper<People> {

}
