package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * teacher
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements Serializable {

    public Teacher(Integer tId, String tName, Integer tGender, Integer tAge, String tPhone, String tQq, String tWx,
                   String tMailbox, String tIntroduce, Integer tPictureId) {
        this.tId = tId;
        this.tName = tName;
        this.tGender = tGender;
        this.tAge = tAge;
        this.tPhone = tPhone;
        this.tQq = tQq;
        this.tWx = tWx;
        this.tMailbox = tMailbox;
        this.tIntroduce = tIntroduce;
        this.tPictureId = tPictureId;
    }

    public Teacher(String tName, String tPhone, String tPassword, String tMailbox, Integer tRoleId) {
        this.tName = tName;
        this.tPhone = tPhone;
        this.tPassword = tPassword;
        this.tMailbox = tMailbox;
        this.tRoleId = tRoleId;
    }

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

    private static final long serialVersionUID = 1L;
}