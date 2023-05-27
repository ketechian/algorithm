package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * picture
 * @author 
 */
@Data
@NoArgsConstructor
public class Picture implements Serializable {
    public Picture(String ptMetaName, String ptName, String ptType, Integer ptPathId) {
        this.ptMetaName = ptMetaName;
        this.ptName = ptName;
        this.ptType = ptType;
        this.ptPathId = ptPathId;
    }

    /**
     * 图片id
     */
    private Integer ptId;

    /**
     * 原图片名
     */
    private String ptMetaName;

    /**
     * uuid
     */
    private String ptName;

    /**
     * 图片类型
     */
    private String ptType;

    /**
     * 图片路径id
     */
    private Integer ptPathId;

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