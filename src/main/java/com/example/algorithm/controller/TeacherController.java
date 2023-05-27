package com.example.algorithm.controller;

import com.example.algorithm.domain.dto.HyperlinkDto;
import com.example.algorithm.domain.dto.InformationGroupDto;
import com.example.algorithm.domain.dto.TaskDto;
import com.example.algorithm.service.TeacherService;
import com.example.algorithm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.DocFlavor;
import java.io.IOException;
import java.text.ParseException;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 19:33
 */

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 创建班级
     *
     * @param className
     * @param openTime
     * @param endTime
     * @param classStatus
     * @param teacherId
     * @param introduce
     * @param headPicture
     * @return
     * @throws ParseException
     * @throws IOException
     */
    @PostMapping("/createClass")
    public Result createClassByTeacher(@RequestParam String className,
                                       @RequestParam String openTime,
                                       @RequestParam String endTime,
                                       @RequestParam String classStatus,
                                       @RequestParam Integer teacherId,
                                       @RequestParam String introduce,
                                       @RequestParam MultipartFile headPicture) throws ParseException, IOException {
        return teacherService.createClass(className, openTime, endTime, classStatus, teacherId, introduce, headPicture, "teacher/createClass");
    }

    /**
     * 批量导入学生
     *
     * @param classId
     * @param studentExcel
     * @return
     */
    @PostMapping("/inputByExcel")
    public Result inputByExcel(@RequestParam Integer classId,
                               @RequestParam MultipartFile studentExcel) {
        return teacherService.inputStudent(classId, studentExcel, "teacher/inputByExcel");
    }

    /**
     * 添加带图片的题目到题库
     *
     * @param type
     * @param topicText
     * @param option
     * @param answer
     * @param topicPicture
     * @param hyperlinkId
     * @param teacherId
     * @return
     */
    @PostMapping("/addTopicWithPicture")
    public Result addTopicWithPicture(@RequestParam String topicName,
                                    @RequestParam Integer type,
                                    @RequestParam String topicText,
                                    @RequestParam String option,
                                    @RequestParam String answer,
                                    @RequestParam MultipartFile topicPicture,
                                    @RequestParam Integer hyperlinkId,
                                    @RequestParam Integer teacherId) throws IOException {
        return teacherService.addTopicWithPicture(topicName, type, topicText, option, answer, topicPicture,
                hyperlinkId, teacherId, "teacher/addTopicWithPicture");
    }

    /**
     * 添加不带图片的题目到题库
     *
     * @param topicName
     * @param type
     * @param topicText
     * @param option
     * @param answer
     * @param hyperlinkId
     * @param teacherId
     * @return
     */
    @PostMapping("/addTopicNoPicture")
    public Result addTopicNoPicture(@RequestParam String topicName,
                                    @RequestParam Integer type,
                                    @RequestParam String topicText,
                                    @RequestParam String option,
                                    @RequestParam String answer,
                                    @RequestParam Integer hyperlinkId,
                                    @RequestParam Integer teacherId) {
        return teacherService.addTopicNoPicture(topicName, type, topicText, option, answer,
                hyperlinkId, teacherId, "teacher/addTopicNoPicture");
    }

    /**
     * 添加超链接
     * @param hyperlinkDto
     * @return
     */
    @PostMapping("/addHyperlink")
    public Result addHyperlinkByTeacher(@RequestBody HyperlinkDto hyperlinkDto){
        return teacherService.addHyperlink(
                hyperlinkDto.getHyperlinkText(),
                hyperlinkDto.getInformation(),
                hyperlinkDto.getUserId(),
                hyperlinkDto.getRoleId(), "teacher/addHyperlink");
    }

    /**
     * 发布消息
     * @param informationGroupDto
     * @return
     */
    @PostMapping("/addInformation")
    public Result addInformation (@RequestBody InformationGroupDto informationGroupDto) {
        return teacherService.addInformation(
                informationGroupDto.getIgContent(),
                informationGroupDto.getIgForId(),
                informationGroupDto.getIgFromClass(),
                informationGroupDto.getIgFromAll(),
                informationGroupDto.getIgRoleId(), "teacher/addInformation"
        );
    }

    /**
     * 发布任务
     * @param taskDto
     * @return
     */
    @PostMapping("/addTask")
    public Result addTask(@RequestBody TaskDto taskDto) {
        return teacherService.addTask(
                taskDto.getTgTopicId(),
                taskDto.getTgForId(),
                taskDto.getTgFromClass(),
                taskDto.getTaskIntroduction(), "teacher/addTask"
        );
    }

    /**
     * 获取个人信息
     * @param teacherId
     * @return
     */
    @GetMapping("/getInformation")
    public Result getInformation(@RequestParam Integer teacherId) {
        return teacherService.getInformation(teacherId, "teacher/getInformation");
    }

    /**
     * 修改个人信息
     * @param teacherId
     * @param name
     * @param gender
     * @param age
     * @param phone
     * @param qq
     * @param wx
     * @param mailbox
     * @param introduce
     * @param headPicture
     * @return
     * @throws IOException
     */
    @PostMapping("/updateInformation")
    public Result updateInformation(@RequestParam Integer teacherId,
                                    @RequestParam String name,
                                    @RequestParam Integer gender,
                                    @RequestParam Integer age,
                                    @RequestParam String phone,
                                    @RequestParam String qq,
                                    @RequestParam String wx,
                                    @RequestParam String mailbox,
                                    @RequestParam String introduce,
                                    @RequestParam MultipartFile headPicture) throws IOException {
        return teacherService.updateInformation(teacherId, name, gender, age, phone, qq, wx, mailbox,
                introduce, headPicture, "teacher/updateInformation");

    }

    /**
     * 获取班级
     * @param teacherId
     * @return
     */
    @GetMapping("/getClass")
    public Result getClassByTeacher(@RequestParam Integer teacherId) {
        return teacherService.getClassByTeacher(teacherId, "teacher/getClass");
    }

    /**
     * 获取指定班级的学生
     * @param teacherId
     * @param classId
     * @param page
     * @param count
     * @return
     */
    @GetMapping("/getStudentByClass")
    public Result getStudentByClass(@RequestParam Integer teacherId,
                                    @RequestParam Integer classId,
                                    @RequestParam Integer page,
                                    @RequestParam Integer count){
        return teacherService.getStudentByClass(teacherId, classId, page, count, "teacher/getStudentByClass");
    }

    /**
     * 获取通知
     * @param teacherId
     * @param roleId
     * @param page
     * @param count
     * @return
     */
    @GetMapping("/getMessageByTeacherId")
    public Result getMessageByTeacherId(@RequestParam Integer teacherId,
                                        @RequestParam Integer roleId,
                                        @RequestParam Integer page,
                                        @RequestParam Integer count) {
        return teacherService.getMessageByTeacherId(teacherId, roleId, page, count, "teacher/getMessageByTeacherId");
    }

    /**
     *查收消息
     * @param messageId
     * @return
     */
    @PostMapping("/checkMessage")
    public Result checkMessage(@RequestParam Integer messageId) {
        return teacherService.checkMassage(messageId, "teacher/checkMessage");
    }

    /**
     * 获取单个通知
     * @param messageId
     * @return
     */
    @GetMapping("/getMessageById")
    public Result getMessageById(@RequestParam Integer messageId) {
        return teacherService.getMessageById(messageId, "teacher/getMessageById");
    }

    /**
     *获取所有审核
     * @param teacherId
     * @param page
     * @param count
     * @return
     */
    @GetMapping("/getAllExamine")
    public Result getExamine(@RequestParam Integer teacherId,
                             @RequestParam Integer page,
                             @RequestParam Integer count) {
        return teacherService.getAllExamine(teacherId, page, count, "teacher/getAllExamine");
    }

    /**
     * 获取未处理审核
     * @param teacherId
     * @param page
     * @param count
     * @return
     */
    @GetMapping("/getNotProcessedExamine")
    public Result getNotProcessedExamine(@RequestParam Integer teacherId,
                             @RequestParam Integer page,
                             @RequestParam Integer count) {
        return teacherService.getNotProcessedExamine(teacherId, page, count, "teacher/getNotProcessedExamine");
    }

    @GetMapping("/getReleaseTask")
    public Result getReleaseTask(@RequestParam Integer teacherId,
                                 @RequestParam Integer page,
                                 @RequestParam Integer count) {
        return teacherService.getReleaseTask(teacherId, page, count, "teacher/getReleaseTask");
    }

    @GetMapping("/getOneExamine")
    public Result getOneExamine(@RequestParam Integer examineId) {
        return teacherService.getOneExamine(examineId, "teacher/getOneExamine");
    }

    @PostMapping("/checkExamine")
    public Result checkExamine(@RequestParam Integer examineId) {
        return teacherService.checkExamine(examineId, "teacher/checkExamine");
    }
}

