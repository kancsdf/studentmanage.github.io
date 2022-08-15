package com.tmy.studentmanage.service.userservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tmy.studentmanage.pojo.User;

import java.util.List;

/**
 * @author 谭明扬
 * @version 8.0
 */
public interface UserService extends IService<User> {
    List<User> getAll();
}
