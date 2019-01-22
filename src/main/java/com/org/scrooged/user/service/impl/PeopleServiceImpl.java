package com.org.scrooged.user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.org.scrooged.user.dao.PeopleDao;
import com.org.scrooged.user.entity.People;
import com.org.scrooged.user.service.IPeopleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Design By Scrooged
 * @version 1.0
 * @description
 * @date 2019/1/16 15:25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PeopleServiceImpl extends ServiceImpl<PeopleDao, People> implements IPeopleService {

    @Override
    public List<People> queryAll() {
        return this.selectList(null);
    }
}
