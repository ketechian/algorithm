package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * path
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Path implements Serializable {
    public Path(String phName, Integer phFatherId, Integer phLayer) {
        this.phName = phName;
        this.phFatherId = phFatherId;
        this.phLayer = phLayer;
    }

    /**
     * 图片路径id
     */
    private Integer phId;

    /**
     * 路径名
     */
    private String phName;

    /**
     * 上一层id
     */
    private Integer phFatherId;

    /**
     * 层数
     */
    private Integer phLayer;

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