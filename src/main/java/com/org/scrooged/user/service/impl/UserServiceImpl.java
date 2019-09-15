package com.org.scrooged.user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.org.scrooged.user.config.IFileService;
import com.org.scrooged.user.dao.UserDao;
import com.org.scrooged.user.entity.ExportEntity;
import com.org.scrooged.user.entity.User;
import com.org.scrooged.user.service.IUserService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * <p>Title: UserServiceImpl</p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai oneself 2019</p>
 *
 * @author luyangqian
 * version 1.0
 * <pre>History
 *          2019-09-12   luyangqian  Created
 * </pre>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService, IFileService {
    @Override
    public void addFile(File file) {

    }

    @Override
    public void exportEntities(List<ExportEntity> exportEntities) {
        System.out.println("hello world hello world hello world hello world hello world hello world hello world " +
                "hello world hello world hello world hello world hello world hello world hello world " +
                "hello world hello world hello world hello world hello world hello world hello world ");
    }
}
