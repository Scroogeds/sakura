package com.org.scrooged.user.controller;

import com.org.scrooged.user.entity.People;
import com.org.scrooged.user.service.IPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Design By Scrooged
 * @version 1.0
 * @description
 * @date 2019/1/16 15:31
 */
@RestController
@RequestMapping("/sakura")
public class PeopleController {

    @Autowired
    private IPeopleService peopleService;

    @GetMapping("/people")
    public List<People> queryAll(){
        return peopleService.queryAll();
    }

}
