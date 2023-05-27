package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * admin
 * @author 
 */
@Data
@NoArgsConstructor
public class Admin implements Serializable {
    /**
     * 管理员id
     */
    private Integer adId;

    /**
     * 管理员编号
     */
    private String adNumber;

    /**
     * 管理员姓名
     */
    private String adName;

    /**
     * 管理员手机号
     */
    private String adPhone;

    /**
     * 管理员密码,通过md5加密
     */
    private String adPassword;

    /**
     * 管理员邮箱
     */
    private String adMailbox;

    /**
     * 管理员头像,图片id
     */
    private Integer adPictureId;

    /**
     * 权限组id
     */
    private Integer adRoleId;

    /**
     * 1为存在, 2为删除
     */
    private Boolean isDelete;

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