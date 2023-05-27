package com.example.algorithm.service;

import com.example.algorithm.utils.Result;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/20 00:41
 */
public interface TopicService {
    Result gatAllTopic(String path);

    Result getTopic(Integer topicId, String path);

    Result getComment(Integer topicId, Integer page, Integer count, String path);


    Result like(Integer commentId, String path);

    Result unlike(Integer commentId, String path);
}
