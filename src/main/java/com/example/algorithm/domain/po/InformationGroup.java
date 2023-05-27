package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * information_group
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformationGroup implements Serializable {

    public InformationGroup(String igContent, Integer igForId, Integer igRoleId, Integer igFromAll, Integer igFromClass) {
        this.igContent = igContent;
        this.igForId = igForId;
        this.igRoleId = igRoleId;
        this.igFromAll = igFromAll;
        this.igFromClass = igFromClass;
    }

    /**
     * 消息id
     */
    private Integer igId;

    /**
     * 消息内容
     */
    private String igContent;

    /**
     * 发布人id
     */
    private Integer igForId;

    /**
     * 权限组id
     */
    private Integer igRoleId;

    /**
     * 1为是, 2为否, 需要权限
     */
    private Integer igFromAll;

    /**
     * 发布班级id,需要权限
     */
    private Integer igFromClass;

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