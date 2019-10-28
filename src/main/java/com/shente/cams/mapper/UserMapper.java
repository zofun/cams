package com.shente.cams.mapper;


import com.shente.cams.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    User queryUserById(@Param("id") Integer id);

    /**
     *  插入用户
     * @param user 包含账号与密码
     */
    @Insert("insert into user(account,password) values(#{account},#{password})")
    @Options(useGeneratedKeys = true,keyColumn = "u_id",keyProperty = "uId")
    int insert(User user);


    /**
     * 根据account查询用户
     * @param account 账号
     */
    @Select("select * from user where account=#{account}")
    @Results({
            @Result(column = "u_id",property = "uId")
    })
    User selectUserByAccount(String account);
}
