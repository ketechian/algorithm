package com.example.algorithm.domain.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/18 01:33
 */
@Data
public class HyperlinkDto {
    /**
     * 超链接内容
     */
    String hyperlinkText;

    /**
     * 超链接描述
     */
    String information;

    /**
     * 添加老师id
     */
    Integer userId;

    /**
     * 老师权限
     */
    Integer roleId;
}
