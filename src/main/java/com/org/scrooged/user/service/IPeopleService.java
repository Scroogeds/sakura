package com.org.scrooged.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.org.scrooged.user.entity.People;

import java.util.List;

/**
 * @author Design By Scrooged
 * @version 1.0
 * @description
 * @date 2019/1/16 15:24
 */
public interface IPeopleService extends IService<People> {

    List<People> queryAll();

    //void addFile(File file);

    People getPeople(String pId);

    void exportFile();

    void importFile();
}
