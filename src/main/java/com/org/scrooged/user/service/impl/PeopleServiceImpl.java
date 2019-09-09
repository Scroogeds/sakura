package com.org.scrooged.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.org.scrooged.user.config.IFileService;
import com.org.scrooged.user.dao.PeopleDao;
import com.org.scrooged.user.entity.ExportEntity;
import com.org.scrooged.user.entity.People;
import com.org.scrooged.user.service.IPeopleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;

/**
 * @author Design By Scrooged
 * @version 1.0
 * @description
 * @date 2019/1/16 15:25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PeopleServiceImpl extends ServiceImpl<PeopleDao, People> implements IPeopleService, IFileService {

    @Override
    public List<People> queryAll() {
        return this.selectList(null);
    }

    @Override
    public void addFile(File file) {
        System.out.println("--------------have a file-----------------");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String txt = null;
            //ExportEntity<People> exportEntity = new ExportEntity<>();
            while (null != (txt = bufferedReader.readLine())){
                //String operate = ((Map)JSON.parse(txt)).get("operate").toString();
                //People p = JSON.parseObject(((Map)JSON.parse(txt)).get("t").toString(), People.class);
                ExportEntity exportEntity = JSON.parseObject(txt, ExportEntity.class);
                if ("update".equals(exportEntity.getOperate())) {
                    updateById((People) exportEntity.getT());
                    //updateById(JSON.parseObject(exportEntity.getT().toString(), People.class));
                }else if ("insert".equals(exportEntity.getOperate())) {
                    insert(JSON.parseObject(exportEntity.getT().toString(), People.class));
                } else {
                    deleteById(JSON.parseObject(exportEntity.getT().toString(), People.class).getpId());
                }
                //System.out.println(exportEntity.getT());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportFile() {
        List<People> peoples = queryAll();
        //List<ExportEntity> exportEntities = new ArrayList<>(peoples.size());
        //StringBuffer buffer = new StringBuffer();
        File file = new File("/Users/luyangqian/file-listen/people.txt");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            for (People people : peoples) {
                ExportEntity<People> exportEntity = new ExportEntity<>();
                exportEntity.setOperate("update");
                exportEntity.setT(people);
                //exportEntities.add(exportEntity);
                //buffer.append(JSON.toJSONString(exportEntity));
                bufferedWriter.write(JSON.toJSONString(exportEntity));
                bufferedWriter.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void importFile() {

    }
}
