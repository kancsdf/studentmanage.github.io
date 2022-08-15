package com.tmy.studentmanage.service.studentservice;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tmy.studentmanage.dao.StudentDao;
import com.tmy.studentmanage.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 谭明扬
 * @version 8.0
 */
@Service
@SuppressWarnings("all")
public class StudentServiceImpl  extends ServiceImpl<StudentDao, Student> implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private Student student;

    /**
     * 查询全部学生信息
     * @return 返回一个list的学生集合
     */
    @Override
    public List<Student> getAll(){
        return studentDao.selectList(null);
    }

    /**
     * 条件查询：根据学生的姓名，年龄, 班级进行查询
     * @param name
     * @param ...nums
     * @return 返回一个学生集合
     */
    @Override
    public LambdaQueryWrapper<Student> getStuBySome(String name, Integer... nums) {
        if(name != "") {
            if (nums[0] != null && nums[1] != null) {
                LambdaQueryWrapper<Student> queryWrapper = Wrappers.<Student>lambdaQuery()
                        .eq(Student::getName, name)
                        .eq(Student::getAge, nums[0])
                        .eq(Student::getClasses, nums[1]);
                return queryWrapper;
            }
            else if (nums[0] != null && nums[1] ==null){
                LambdaQueryWrapper<Student> queryWrapper = Wrappers.<Student>lambdaQuery()
                        .eq(Student::getName, name)
                        .eq(Student::getAge, nums[0]);
                return queryWrapper;
            }
           else if(nums[0] == null && nums[1] != null){
                LambdaQueryWrapper<Student> queryWrapper = Wrappers.<Student>lambdaQuery()
                        .eq(Student::getName, name)
                        .eq(Student::getClasses, nums[1]);
                return queryWrapper;
            }
            else {
                LambdaQueryWrapper<Student> queryWrapper = Wrappers.<Student>lambdaQuery()
                        .eq(Student::getName, name);
                return queryWrapper;
            }
        }
        if (name.equals("")) {
            LambdaQueryWrapper<Student> queryWrapper = Wrappers.<Student>lambdaQuery();
            if (nums[0] != null && nums[1] == null) {
                queryWrapper.eq(Student::getAge, nums[0]);
                return queryWrapper;
            } else if (nums[0] == null && nums[1] != null) {
                queryWrapper.eq(Student::getClasses, nums[1]);
                return queryWrapper;
            } else {
                queryWrapper.eq(Student::getAge, nums[0]).eq(Student::getClasses, nums[1]);
                return queryWrapper;
            }
        }
        return null;
    }

    /**
     * 根据student对象的信息判断执行什么方法
     * @param student
     * @return 返回一个学生集合
     */
    @Override
    public LambdaQueryWrapper<Student> judgeDoIt(Student student) {
            return getStuBySome(student.getName(), student.getAge(), student.getClasses());
    }

    /**
     * 根据条件进行分页查询
     * @param current
     * @param size
     * @param student
     * @return page对象
     */
    @Override
    public IPage<Student> getPage(Long current ,Long size,Student student){
        if(student != null){
            LambdaQueryWrapper<Student>  lambdaQueryWrapper =  judgeDoIt(student);
            Page<Student> studentPage = new Page<>(current,size);
            Page<Student> page = studentDao.selectPage(studentPage, lambdaQueryWrapper);
            return page;
        }
        else return null;
    }

    @Override
    public IPage<Student> selectByPage(Long current, Long size) {
        Page<Student> studentIPage = new Page<>(current, size);
        Page<Student> page = studentDao.selectPage(studentIPage,null);
        //将page对象转化为List集合
        List<Student> students = page.getRecords();
        if(students != null){
            Integer reference = 0;
            for (int i = 1; i <= students.size() -1; i++){
                if(students.get(i -1).getId() + 1 == students.get(i).getId()){
                    continue;
                }else {
                    Integer num = students.get(i).getId() - students.get(i - 1).getId() -1;
                    students.get(i).setId(students.get(i).getId() - num);
                    continue;
                }
            }
            return page.setRecords(students);//将已经转为list集合的page对象转变回来
        }
        return null;
    }
}
