package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * teacher_class
 * @author 
 */
@Data
@NoArgsConstructor
public class TeacherClass implements Serializable {
    public TeacherClass(Integer tcClassId, Integer tcTeacherId) {
        this.tcClassId = tcClassId;
        this.tcTeacherId = tcTeacherId;
    }

    /**
     * 老师班级关系id
     */
    private Integer tcId;

    /**
     * 班级id
     */
    private Integer tcClassId;

    /**
     * 老师id
     */
    private Integer tcTeacherId;

    /**
     * 1为存在, 0为删除
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