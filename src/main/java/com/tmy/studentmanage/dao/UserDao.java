package com.tmy.studentmanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tmy.studentmanage.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 谭明扬
 * @version 8.0
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}
