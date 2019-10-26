package com.shente.cams.mapper;


import com.shente.cams.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    User queryUserById(@Param("id") Integer id);
}
