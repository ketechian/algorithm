package com.example.algorithm.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/19 12:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    private Integer totalCount;

    private Integer currentPage;

    private Integer totalPage;

    private Integer size;

    private List<T> data;

    public static Integer getTotalPage(Integer size, Integer totalCount){
        if (totalCount/size == 0){
            return 1;
        } else if (totalCount/size != 0 && totalCount % size == 0){
            return totalCount/size;
        }else {
            return totalCount/size + 1;
        }
    }
}
