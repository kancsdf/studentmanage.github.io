package com.tmy.studentmanage.service.studentservice;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tmy.studentmanage.pojo.Student;

import java.util.List;

/**
 * @author 谭明扬
 * @version 8.0
 */
public interface StudentService extends IService<Student> {
    List<Student> getAll();
    LambdaQueryWrapper<Student> getStuBySome(String name, Integer...nums);
    LambdaQueryWrapper<Student> judgeDoIt(Student student);
    IPage<Student> getPage(Long current , Long size, Student student);
    IPage<Student> selectByPage(Long current,Long size);
}
