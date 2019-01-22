package com.org.scrooged;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Design By Scrooged
 * @version 1.0
 * @description
 * @date 2019/1/16 8:55
 */
@EnableTransactionManagement
@MapperScan(basePackages = {"com.org.scrooged.user.dao"})
@SpringBootApplication
public class SakuraApplication {

    private static Logger logger = LoggerFactory.getLogger(SakuraApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(SakuraApplication.class, args);

        logger.info("----------踏遍青山人未老，风景这边独好----------");
    }
}
