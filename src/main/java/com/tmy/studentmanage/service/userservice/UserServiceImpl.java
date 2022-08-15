package com.tmy.studentmanage.service.userservice;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tmy.studentmanage.dao.UserDao;
import com.tmy.studentmanage.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谭明扬
 * @version 8.0
 */
@Service
@SuppressWarnings("all")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<User> getAll() {
        return userDao.selectList(null);
    }
}
