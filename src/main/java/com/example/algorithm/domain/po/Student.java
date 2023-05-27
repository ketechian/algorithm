package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * student
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

    public Student(Integer stId, String stNumber, String stName, int stGender, Integer stAge, String stPhone,
                   String stQq, String stWx, String stMailbox, Integer stPictureId) {
        this.stId = stId;
        this.stNumber = stNumber;
        this.stName = stName;
        this.stGender = stGender;
        this.stAge = stAge;
        this.stPhone = stPhone;
        this.stQq = stQq;
        this.stWx = stWx;
        this.stMailbox = stMailbox;
        this.stPictureId = stPictureId;
    }

    public Student(String stName, String stPhone, String stPassword, String stMailbox, Integer stRoleId) {
        this.stName = stName;
        this.stPhone = stPhone;
        this.stPassword = stPassword;
        this.stMailbox = stMailbox;
        this.stRoleId = stRoleId;
    }

    public Student(String stNumber, String stName, String stPhone, String stPassword, String stMailbox, Integer stRoleId) {
        this.stNumber = stNumber;
        this.stName = stName;
        this.stPhone = stPhone;
        this.stPassword = stPassword;
        this.stMailbox = stMailbox;
        this.stRoleId = stRoleId;
    }

    /**
     * 学生id
     */
    private Integer stId;

    /**
     * 学生学号
     */
    private String stNumber;
    /**
     * 学生姓名
     */
    private String stName;


    /**
     * 学生性别,1为男生, 2为女生
     */
    private int stGender;

    /**
     * 学生年龄
     */
    private Integer stAge;

    /**
     * 学生手机号
     */
    private String stPhone;

    /**
     * md5加密
     */
    private String stPassword;

    /**
     * 学生qq号
     */
    private String stQq;

    /**
     * 学生微信号
     */
    private String stWx;

    /**
     * 学生邮箱
     */
    private String stMailbox;

    /**
     * 学生头像图片id
     */
    private Integer stPictureId;

    /**
     * 权限组id
     */
    private Integer stRoleId;

    /**
     * 授权id
     */
    private Integer stEmpowerId;

    /**
     * 逻辑删除,1为存在, 2为删除
     */
    private int isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date modifyTime;

    private static final long serialVersionUID = 1L;
}