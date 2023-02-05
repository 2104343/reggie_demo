package com.itheima.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program:reggie_take_out
 * @description:
 * @author:左毅
 * @createData:2022/9/19
 **/
@SpringBootApplication
@Slf4j
@ServletComponentScan
@EnableTransactionManagement
public class RegieeApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegieeApplication.class,args);
        log.info("项目启动成功");
    }
}
