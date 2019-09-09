package com.org.scrooged.user.config;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * <p>Title: FileListenerRunner</p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai oneself 2019</p>
 *
 * @author luyangqian
 * version 1.0
 * <pre>History
 *          2019-08-27   luyangqian  Created
 * </pre>
 */
//@Order(-1)
@Component
public class FileListenerRunner implements CommandLineRunner {

    @Autowired
    private FileListenerFactory fileListenerFactory;

    @Autowired
    private IFileService fileService;

    @Override
    public void run(String... strings) throws Exception {
        // 创建监听者
        System.out.println("fileListenerFactory= "+fileListenerFactory);
        FileAlterationMonitor fileAlterationMonitor = fileListenerFactory.getMonitor(fileService);
        try {
            // do not stop this thread
            fileAlterationMonitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
