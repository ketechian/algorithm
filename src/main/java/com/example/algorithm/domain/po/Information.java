package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * information
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Information implements Serializable {

    /**
     * 消息id
     */
    private Integer imId;

    /**
     * 父消息id
     */
    private Integer imFatherId;

    /**
     * 发布到具体角色id
     */
    private Integer imFromId;

    /**
     * 1为查收, 2为未查收
     */
    private Integer isCheck;

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

    private String imText;

    private Integer imRoleId;

    private static final long serialVersionUID = 1L;
}