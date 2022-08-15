package com.tmy.studentmanage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tmy.studentmanage.pojo.Student;
import com.tmy.studentmanage.result.Result;
import com.tmy.studentmanage.service.studentservice.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 谭明扬
 * @version 8.0
 */
@SuppressWarnings("all")
@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    private Boolean flag = true;

    @GetMapping
    public Result getAll() {
        return new Result(flag, studentService.getAll(),null);
    }

    @PostMapping
    public Result putStu(@RequestBody Student student) {
        Boolean key = studentService.save(student);
        return new Result(key,null,key? "添加成功^_^":"添加失败-_-!");
    }

    @PutMapping
    public Result update(@RequestBody Student student) {
        boolean key = studentService.updateById(student);
        return new Result(key,null,key? "添加成功^_^":"添加失败-_-!");
    }

    @DeleteMapping("/{id}")
    public Result delStu(@PathVariable Integer id) {
        boolean key = studentService.removeById(id);
        return new Result(key,null ,key ? "删除成功^_^":"删除失败-_-!");
    }

    @GetMapping("/{current}/{size}")
    public Result pageStu(@PathVariable Long current, @PathVariable Long size) {
        IPage<Student> page =  studentService.selectByPage(current,size);
        if(current > page.getCurrent()){
            studentService.page(new Page<Student>(current, size));
        }
        return new Result(flag, page, null);
    }
    @PostMapping("/find/{current}/{size}")
    public Result pageStu(@PathVariable Long current, @PathVariable Long size, @RequestBody Student student) {
        IPage<Student> page = studentService.getPage(current, size, student);
        return new Result(flag, page, null);
    }
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        return new Result(flag,studentService.getById(id),flag? "查询成功^_^":"查询失败-_-!");
    }
}
