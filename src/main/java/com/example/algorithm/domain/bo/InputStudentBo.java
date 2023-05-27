package com.example.algorithm.domain.bo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/17 12:42
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget("InputStudentBo")
public class InputStudentBo implements Serializable {
    /**
     * 学生姓名
     */
    @Excel(name = "姓名", width = 20, isImportField = "true_st")
    private String name;

    /**
     * 学生学号
     */
    @Excel(name = "学号", width = 20, isImportField = "true_st")
    private String number;

    /**
     * 学生手机号
     */
    @Excel(name = "手机号", width = 20, isImportField = "true_st")
    private String phone;

    /**
     * 学生邮箱
     */
    @Excel(name = "邮箱", width = 20, isImportField = "true_st")
    private String mailbox;

    private String password;
}
