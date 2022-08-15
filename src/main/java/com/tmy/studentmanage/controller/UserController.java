package com.tmy.studentmanage.controller;

import com.tmy.studentmanage.pojo.User;
import com.tmy.studentmanage.result.Result;
import com.tmy.studentmanage.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author 谭明扬
 * @version 8.0
 */
@SuppressWarnings("all")
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private Boolean flag = true;
    @GetMapping
    public Result getAll(){
        return new Result(flag,userService.getAll(),null);
    }
    @PostMapping
    public Result putUser(@RequestBody User user){
        boolean key = userService.save(user);
        return new Result(key,null,key? "添加成功 ^_^" :" 添加失败-_-!");
    }
    @PutMapping
    public Result update(@RequestBody User user){
        Boolean key = userService.updateById(user);
        return new Result(key,null,key? "修改成功 ^_^" :" 修改失败-_-!");
    }
    @DeleteMapping("/{userName}")
    public Result delUser(@PathVariable String userName){
        Boolean key = userService.removeById(userName);
        return new Result(key,null,key? "删除成功 ^_^" :" 删除失败-_-!");
    }
    @GetMapping("/{userName}")
    public Result getUserById(@PathVariable String userName){
        return new Result(flag ,userService.getById(userName),flag?"查询成功 ^_^" :" 查询失败-_-!");
    }
}
