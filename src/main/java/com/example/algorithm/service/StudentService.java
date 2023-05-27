package com.example.algorithm.service;

import com.example.algorithm.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/18 19:32
 */
public interface StudentService {
    Result addComment(Integer cmTopicId, Integer cmUserId, Integer cmUserRole, String cmContent, String path);

    Result getInformation(Integer teacherId, String path);

    Result updateInformation(Integer studentId, String name, String studentNum, Integer gender, Integer age, String phone, String qq,
                             String wx, String mailbox, MultipartFile headPicture, String path) throws IOException;

    Result getClassByStudent(Integer teacherId, String path);

    Result getMessageByStudentId(Integer studentId, Integer roleId, Integer page, Integer count, String path);

    Result checkMassage(Integer messageId, String path);

    Result makeQuestionNoPicture(Integer studentId, Integer topicId, String answer, String path);

    Result makeQuestionWithPicture(Integer studentId, Integer topicId, MultipartFile answerPicture, String path) throws IOException;

    Result getMessageById(Integer messageId, String path);

    Result makeTakeNoPicture(Integer studentId, Integer topicId, Integer taskId, String answer, String path);

    Result getAllTask(Integer studentId, Integer page, Integer count, String path);

    Result checkTask(Integer taskId, String path);

    Result getOneTask(Integer taskId, String path);

    Result makeTakeWithPicture(Integer studentId, Integer topicId, Integer taskId, MultipartFile answerPicture, String path) throws IOException;
}
