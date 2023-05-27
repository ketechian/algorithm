package com.example.algorithm.service;

import com.example.algorithm.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 19:38
 */
public interface TeacherService {
    /**
     * 创建班级
     * @param className
     * @param sOpenTime
     * @param sEndTime
     * @param classStatus
     * @param teacherId
     * @param introduce
     * @param headPicture
     * @param path
     * @return
     */
    Result createClass(String className, String sOpenTime, String sEndTime, String classStatus, Integer teacherId,
                      String introduce, MultipartFile headPicture, String path) throws ParseException, IOException;

    /**
     *导入学生
     * @param classId
     * @param studentExcel
     * @param path
     * @return
     */
    Result inputStudent(Integer classId, MultipartFile studentExcel, String path);

    /**
     * 添加有图片的题目
     * @param type
     * @param topicText
     * @param option
     * @param answer
     * @param topicPicture
     * @param hyperlinkId
     * @param teacherId
     * @param path
     * @return
     */
    Result addTopicWithPicture(String topicName, Integer type, String topicText, String option, String answer,
                    MultipartFile topicPicture, Integer hyperlinkId, Integer teacherId, String path) throws IOException;


    /**
     * 添加超链接
     * @param hyperlink
     * @param information
     * @param userId
     * @return
     */
    Result addHyperlink(String hyperlink, String information, Integer userId, Integer roleId, String path);

    /**
     * 添加没有图片的题目
     * @param topicName
     * @param type
     * @param topicText
     * @param option
     * @param answer
     * @param hyperlinkId
     * @param teacherId
     * @param path
     * @return
     */
    Result addTopicNoPicture(String topicName, Integer type, String topicText, String option, String answer,
                             Integer hyperlinkId, Integer teacherId, String path);

    /**
     * 群发消息
     * @param informationContent
     * @param informationForId
     * @param informationFromClass
     * @param informationFromAll
     * @param roleId
     * @param path
     * @return
     */
    Result addInformation(String informationContent, Integer informationForId, Integer informationFromClass, Integer informationFromAll,
                          Integer roleId, String path);

    /**
     * 发布任务
     * @param tgTopicId
     * @param tgForId
     * @param tgFromClass
     * @param path
     * @return
     */
    Result addTask(String tgTopicId, Integer tgForId, Integer tgFromClass, String taskIntroduction, String path);

    /**
     * 获取老师个人信息
     * @param teacherId
     * @param path
     * @return
     */
    Result getInformation(Integer teacherId, String path);

    /**
     * 修改信息
     * @param name
     * @param gender
     * @param age
     * @param phone
     * @param qq
     * @param wx
     * @param mailbox
     * @param introduce
     * @param headPicture
     * @param path
     * @return
     */
    Result updateInformation(Integer teacherId, String name, Integer gender, Integer age, String phone, String qq, String wx,
                             String mailbox, String introduce, MultipartFile headPicture, String path) throws IOException;

    /**
     * 获取班级
     * @param teacherId
     * @param path
     * @return
     */
    Result getClassByTeacher(Integer teacherId, String path);

    /**
     *
     * @param teacherId
     * @param classId
     * @param page
     * @param count
     * @param path
     * @return
     */
    Result getStudentByClass(Integer teacherId, Integer classId, Integer page, Integer count, String path);

    /**
     *
     * @param teacherId
     * @param page
     * @param count
     * @param path
     * @return
     */
    Result getMessageByTeacherId(Integer teacherId, Integer roleId, Integer page, Integer count, String path);

    /**
     *
     * @param messageId
     * @param path
     * @return
     */
    Result checkMassage(Integer messageId, String path);

    /**
     *
     * @param teacherId
     * @param page
     * @param count
     * @param path
     * @return
     */
    Result getAllExamine(Integer teacherId, Integer page, Integer count, String path);

    /**
     *
     * @param teacherId
     * @param page
     * @param count
     * @param path
     * @return
     */
    Result getNotProcessedExamine(Integer teacherId, Integer page, Integer count, String path);

    /**
     *
     * @param messageId
     * @param path
     * @return
     */
    Result getMessageById(Integer messageId, String path);

    /**
     *
     * @param teacherId
     * @param page
     * @param count
     * @param path
     * @return
     */
    Result getReleaseTask(Integer teacherId, Integer page, Integer count, String path);

    /**
     *
     * @param examineId
     * @param path
     * @return
     */
    Result getOneExamine(Integer examineId, String path);


    Result checkExamine(Integer examineId, String path);
}
