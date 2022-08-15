package com.tmy.studentmanage.result;

import lombok.Data;

/**
 * @author 谭明扬
 * @version 8.0
 */
@Data
public class Result {
    private Boolean flag;
    private Object data;
    private String mes;
    public Result(){};

    public Result(Boolean flag, Object data, String mes) {
        this.flag = flag;
        this.data = data;
        this.mes = mes;
    }

    public Result(Boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public Result(Boolean flag, String mes) {
        this.flag = flag;
        this.mes = mes;
    }
}
