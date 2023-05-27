package com.example.algorithm.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/20 15:22
 */
@Data
public class StudentVo {
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

    private String stPicturePath;

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
}
