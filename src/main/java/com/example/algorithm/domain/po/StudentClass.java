package com.example.algorithm.domain.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * student_class
 * @author 
 */
@Data
@NoArgsConstructor
public class StudentClass implements Serializable {
    public StudentClass(Integer scClassId, Integer scStudentId) {
        this.scClassId = scClassId;
        this.scStudentId = scStudentId;
    }

    /**
     * 学生班级关系id
     */
    private Integer scId;

    /**
     * 班级id
     */
    private Integer scClassId;

    /**
     * 学生id
     */
    private Integer scStudentId;

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