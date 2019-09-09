package com.org.scrooged.user.config;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: FileListenerFactory</p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai oneself 2019</p>
 *
 * @author luyangqian
 * version 1.0
 * <pre>History
 *          2019-08-27   luyangqian  Created
 * </pre>
 */
@Component
public class FileListenerFactory {
    // 设置监听路径
    private final String monitorDir = "/Users/luyangqian/file-listen";
    // 设置轮询间隔
    private final long interval = TimeUnit.SECONDS.toMillis(1);

    // 自动注入业务服务
    /*@Autowired
    private IPeopleService peopleService;*/

    public FileAlterationMonitor getMonitor(IFileService...fileServices) {
        // 创建过滤器
        System.out.println("===in FileAlterationMonitor=== ");
        IOFileFilter directories = FileFilterUtils.and(
                FileFilterUtils.directoryFileFilter(),
                HiddenFileFilter.VISIBLE);
        IOFileFilter files = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),
                FileFilterUtils.suffixFileFilter(".txt"));
        IOFileFilter filter = FileFilterUtils.or(directories, files);

        // 装配过滤器
        // FileAlterationObserver observer = new FileAlterationObserver(new File(monitorDir));
        FileAlterationObserver observer = new FileAlterationObserver(new File(monitorDir), filter);
        System.out.println("observer= "+observer);
        // 向监听者添加监听器，并注入业务服务
        observer.addListener(new FileListener(fileServices));
        //        observer.addListener(new FileListener());

        // 返回监听者
        return new FileAlterationMonitor(interval, observer);
    }

}
