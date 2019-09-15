package com.org.scrooged.user.config;

import com.alibaba.fastjson.JSON;
import com.org.scrooged.user.entity.ExportEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: FileParserUtils</p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai oneself 2019</p>
 *
 * @author luyangqian
 * version 1.0
 * <pre>History
 *          2019-09-12   luyangqian  Created
 * </pre>
 */
public class FileParserUtils {

    public static List<ExportEntity> parserEntity(File file){
        List<ExportEntity> exportEntities = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String txt = null;
            while (null != (txt = bufferedReader.readLine())){
                exportEntities.add(JSON.parseObject(txt, ExportEntity.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exportEntities;
    }
}
