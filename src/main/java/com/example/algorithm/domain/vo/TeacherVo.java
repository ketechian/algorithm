package com.example.algorithm.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/20 02:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherVo {
    /**
     * 老师id
     */
    private Integer tId;

    /**
     * 老师姓名
     */
    private String tName;

    /**
     * 1为男生, 2为女生
     */
    private Integer tGender;

    /**
     * 老师年龄
     */
    private Integer tAge;

    /**
     * 老师手机号
     */
    private String tPhone;

    /**
     * 老师密码
     */
    private String tPassword;

    /**
     * 老师qq号
     */
    private String tQq;

    /**
     * 老师微信号
     */
    private String tWx;

    /**
     * 老师邮箱
     */
    private String tMailbox;

    /**
     * 老师简介
     */
    private String tIntroduce;

    /**
     * 老师头像图片id
     */
    private Integer tPictureId;

    /**
     * 老师头像图片id
     */
    private String tPicturePath;

    /**
     * 权限组id
     */
    private Integer tRoleId;

    /**
     * 授权id
     */
    private Integer tEmpowerId;

    /**
     * 1为存在, 2为删除
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date modifyTime;
}
