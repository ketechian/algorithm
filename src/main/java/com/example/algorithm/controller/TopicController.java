package com.example.algorithm.controller;

import com.example.algorithm.service.TopicService;
import com.example.algorithm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/20 00:42
 */
@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    TopicService topicService;

    @GetMapping("/getAllTopic")
    public Result getAllTopic() {
        return topicService.gatAllTopic("topic/getAllTopic");
    }

    @GetMapping("/getTopic")
    public Result getTopic(@RequestParam Integer topicId) {
        return topicService.getTopic(topicId, "topic/getTopic");
    }
    @GetMapping("/getComment")
    public Result getComment(@RequestParam Integer topicId,
                             @RequestParam Integer page,
                             @RequestParam Integer count) {
        return topicService.getComment(topicId, page, count, "topic/getComment");
    }

    /**
     * 点赞
     * @param commentId
     * @return
     */
    @GetMapping("/like")
    public Result like(@RequestParam Integer commentId) {
        return topicService.like(commentId, "student/like");
    }

    /**
     * 点踩
     * @param commentId
     * @return
     */
    @GetMapping("/unlike")
    public Result unlike(@RequestParam Integer commentId) {
        return topicService.unlike(commentId, "student/unlike");
    }
}
