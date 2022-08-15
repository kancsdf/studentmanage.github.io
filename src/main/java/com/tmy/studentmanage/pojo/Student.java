package com.tmy.studentmanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author 谭明扬
 * @version 8.0
 */
@Data
@Component
public class Student {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private Integer classes;
    private String specialty;
    private String address;

}
