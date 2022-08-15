package com.tmy.studentmanage.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author 谭明扬
 * @version 8.0
 */
@Data
@Component
public class User {
    @TableId
    private String userName;
    private String password;
}
