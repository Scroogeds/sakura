package com.org.scrooged.user.config;

import com.org.scrooged.user.service.IPeopleService;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Title: FileListener</p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai oneself 2019</p>
 *
 * @author luyangqian
 * version 1.0
 * <pre>History
 *          2019-08-27   luyangqian  Created
 * </pre>
 */
//@Component
public class FileListener extends FileAlterationListenerAdaptor {
    private Logger log = LoggerFactory.getLogger(FileListener.class);

    // 声明业务服务
    //@Autowired
    private IPeopleService peopleService;
    private List<IFileService> fileServices;

    // 采用构造函数注入服务
    public FileListener(IFileService... fileService) {
        this.fileServices = Arrays.asList(fileService);
    }

    // 文件创建执行
    @Override
    public void onFileCreate(File file) {
        log.info("[新建]:" + file.getAbsolutePath());
        fileServices.forEach(fileService -> fileService.addFile(file));
        //fileService.addFile(file);
    }

    // 文件创建修改
    @Override
    public void onFileChange(File file) {
        log.info("[修改]:" + file.getAbsolutePath());
        // 触发业务
//        listenerService.doSomething();
    }

    // 文件创建删除
    @Override
    public void onFileDelete(File file) {
        log.info("[删除]:" + file.getAbsolutePath());
    }

    // 目录创建
    @Override
    public void onDirectoryCreate(File directory) {
        log.info("[目录创建]:" + directory.getAbsolutePath());
    }

    // 目录修改
    @Override
    public void onDirectoryChange(File directory) {
        log.info("[目录修改]:" + directory.getAbsolutePath());
    }

    // 目录删除
    @Override
    public void onDirectoryDelete(File directory) {
        log.info("[目录删除]:" + directory.getAbsolutePath());
    }


    // 轮询开始
    @Override
    public void onStart(FileAlterationObserver observer) {
    }

    // 轮询结束
    @Override
    public void onStop(FileAlterationObserver observer) {
    }

}