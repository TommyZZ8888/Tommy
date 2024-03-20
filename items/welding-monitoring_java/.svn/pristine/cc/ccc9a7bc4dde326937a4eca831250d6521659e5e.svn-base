package com.vren.weldingmonitoring_java.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.vren.weldingmonitoring_java.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author GR
 * time 2023-06-30-13-37
 **/
@Mapper
public interface UserMapper extends MPJBaseMapper<User> {

    @Select("select * from user where name = #{name}")
    User findByName(@Param("name") String name);
}
