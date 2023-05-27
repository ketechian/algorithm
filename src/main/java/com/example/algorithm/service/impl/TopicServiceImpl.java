package com.example.algorithm.service.impl;

import com.example.algorithm.domain.po.Comment;
import com.example.algorithm.domain.po.Picture;
import com.example.algorithm.domain.vo.TopicAllVo;
import com.example.algorithm.domain.vo.TopicVo;
import com.example.algorithm.mapper.CommentMapper;
import com.example.algorithm.mapper.PictureMapper;
import com.example.algorithm.mapper.TopicMapper;
import com.example.algorithm.service.TopicService;
import com.example.algorithm.utils.PageBean;
import com.example.algorithm.utils.Result;
import com.example.algorithm.utils.oss.OssFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/20 00:41
 */
@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicMapper topicMapper;

    @Autowired
    PictureMapper pictureMapper;

    @Autowired
    OssFileUtil ossFileUtil;

    @Autowired
    CommentMapper commentMapper;

    /**
     * 获取题目列表
     * @param path
     * @return
     */
    @Override
    public Result gatAllTopic(String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            List<TopicAllVo> allTopicVos = topicMapper.selectAllTopic();
            createCode = 1;
            data.put("createCode", createCode);
            data.put("result", allTopicVos);
        } catch (Exception e) {
            e.printStackTrace();
            data.put("createCode", createCode);
            data.put("result", "获取失败");
        } finally {
            message.put("register_code", createCode);
            message.put("data", data);
        }
        return new Result().result200(message, path);
    }

    /**
     * 获取具体题目信息
     * @param topicId
     * @param path
     * @return
     */
    @Override
    public Result getTopic(Integer topicId, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            TopicVo topicVo = topicMapper.selectTopic(topicId);
            Picture picture = pictureMapper.selectByPrimaryKey(topicVo.getTpPictureId());
            if (picture != null) {
                String fullPath = ossFileUtil.getFullPath(picture.getPtName());
                topicVo.setTpPicturePath(fullPath);
            }
            createCode = 1;
            data.put("createCode", createCode);
            data.put("result", topicVo);
        } catch (Exception e) {
            e.printStackTrace();
            data.put("createCode", createCode);
            data.put("result", "获取失败");
        } finally {
            message.put("register_code", createCode);
            message.put("data", data);
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result getComment(Integer topicId, Integer page, Integer count, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            Integer totalCount = commentMapper.countComment(topicId);
            Integer totalPage = PageBean.getTotalPage(count, totalCount);
            Integer start = (page - 1) * count;
            List<Comment> commentList = commentMapper.selectByTopicId(topicId, start, count);
            PageBean<Comment> commentPage = new PageBean<>(totalCount, page, totalPage, start, commentList);

            createCode = 1;
            data.put("createCode", createCode);
            data.put("result", commentPage);
        } catch (Exception e) {
            e.printStackTrace();
            data.put("createCode", createCode);
            data.put("result", "获取失败");
        } finally {
            message.put("register_code", createCode);
            message.put("data", data);
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result like(Integer commentId, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            commentMapper.updateLike(commentId);
            createCode = 1;
            data.put("createCode", createCode);
            data.put("result", "点赞成功");
        } catch (Exception e) {
            e.printStackTrace();
            data.put("createCode", createCode);
            data.put("result", "点赞失败");
        } finally {
            message.put("register_code", createCode);
            message.put("data", data);
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result unlike(Integer commentId, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            commentMapper.updateUnlike(commentId);
            createCode = 1;
            data.put("createCode", createCode);
            data.put("result", "点踩成功");
        } catch (Exception e) {
            e.printStackTrace();
            data.put("createCode", createCode);
            data.put("result", "点踩失败");
        } finally {
            message.put("register_code", createCode);
            message.put("data", data);
        }
        return new Result().result200(message, path);
    }
}
