package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * permission_group
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionGroup {
    public PermissionGroup(String prName, String prPermission, int isDelete, Date createTime, Date modifyTime) {
        this.prName = prName;
        this.prPermission = prPermission;
        this.isDelete = isDelete;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    /**
     * 权限组id
     */
    private Integer prId;

    /**
     * 权限组名称
     */
    private String prName;

    /**
     * 所拥有权限,以"/"分隔
     */
    private String prPermission;

    /**
     * 1为存在, 2为删除
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