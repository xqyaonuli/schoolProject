package com.example.schoolproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描 mybatis mapper 包路径
@MapperScan(basePackages = "com.example.schoolproject.mapper")     // 这一步别忘了。
//扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
//@ComponentScan(basePackages = {"com.weiz","org.n3r.idworker"})
public class SchoolProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolProjectApplication.class, args);
    }

}
