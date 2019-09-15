package com.org.scrooged.user.config;

import com.org.scrooged.user.entity.ExportEntity;

import java.io.File;
import java.util.List;

/**
 * <p>Title: IFileService</p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai oneself 2019</p>
 *
 * @author luyangqian
 * version 1.0
 * <pre>History
 *          2019-08-28   luyangqian  Created
 * </pre>
 */
public interface IFileService {
    void addFile(File file);

    void exportEntities(List<ExportEntity> exportEntities);

    default void exportEntity(List<ExportEntity> exportEntities){

    }
}
