package com.example.schoolproject.mapper;

import com.example.schoolproject.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User GetUserText(@Param("name") String name);
}
