package com.example.algorithm.controller;

import com.example.algorithm.domain.dto.CommentDto;
import com.example.algorithm.domain.dto.MakeQuestionDto;
import com.example.algorithm.service.StudentService;
import com.example.algorithm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/18 20:02
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/getInformation")
    public Result getInformation(@RequestParam Integer studentId) {
        return studentService.getInformation(studentId, "student/getInformation");
    }

    @PostMapping("/updateInformation")
    public Result updateInformation(@RequestParam Integer studentId,
                                    @RequestParam String name,
                                    @RequestParam String studentNum,
                                    @RequestParam Integer gender,
                                    @RequestParam Integer age,
                                    @RequestParam String phone,
                                    @RequestParam String qq,
                                    @RequestParam String wx,
                                    @RequestParam String mailbox,
                                    @RequestParam MultipartFile headPicture) throws IOException {
        return studentService.updateInformation(studentId, name, studentNum, gender, age, phone, qq, wx, mailbox,
                headPicture, "teacher/updateInformation");
    }

    /**
     * 获取所在班级
     *
     * @param studentId
     * @return
     */
    @GetMapping("/getClass")
    public Result getClassByTeacher(@RequestParam Integer studentId) {
        return studentService.getClassByStudent(studentId, "student/getClass");
    }

    /**
     * 获取通知
     * @param studentId
     * @param roleId
     * @param page
     * @param count
     * @return
     */
    @GetMapping("/getMessageByStudentId")
    public Result getMessageByTeacherId(@RequestParam Integer studentId,
                                        @RequestParam Integer roleId,
                                        @RequestParam Integer page,
                                        @RequestParam Integer count) {
        return studentService.getMessageByStudentId(studentId, roleId, page, count, "student/getMessageByStudentId");
    }

    /**
     * 查收通知
     * @param messageId
     * @return
     */
    @PostMapping("/checkMessage")
    public Result checkMessage(@RequestParam Integer messageId) {
        return studentService.checkMassage(messageId, "student/checkMessage");
    }

    /**
     * 获取单个通知
     * @param messageId
     * @return
     */
    @GetMapping("/getMessageById")
    public Result getMessageById(@RequestParam Integer messageId) {
        return studentService.getMessageById(messageId, "student/getMessageById");
    }

    /**
     * 自由答题(非大题)
     * @param studentId
     * @param topicId
     * @param answer
     * @return
     */
    @PostMapping("/makeQuestionNoPicture")
    public Result makeQuestionNoPicture(
            @RequestParam Integer studentId,
            @RequestParam Integer topicId,
            @RequestParam String answer) {
        return studentService.makeQuestionNoPicture(studentId, topicId, answer, "student/makeQuestionNoPicture");
    }

    /**
     * 自由答大题
     * @param studentId
     * @param topicId
     * @param answerPicture
     * @return
     * @throws IOException
     */
    @PostMapping("/makeQuestionWithPicture")
    public Result makeQuestionWithPicture( @RequestParam Integer studentId,
                                           @RequestParam Integer topicId,
                                           @RequestParam MultipartFile answerPicture) throws IOException {
        return studentService.makeQuestionWithPicture(studentId, topicId, answerPicture, "student/makeQuestionWithPicture");
    }

    /**
     * 获取所有任务
     * @param studentId
     * @param page
     * @param count
     * @return
     */
    @GetMapping("/getAllTask")
    public Result getAllTask(@RequestParam Integer studentId,
                             @RequestParam Integer page,
                             @RequestParam Integer count) {
        return studentService.getAllTask(studentId, page, count, "student/getAllTask");
    }

    /**
     * 查收任务
     * @param taskId
     * @return
     */
    @PostMapping("/checkTask")
    public Result checkTask(@RequestParam Integer taskId){
        return studentService.checkTask(taskId, "student/checkTask");
    }

    /**
     * 获取单个任务
     * @param taskId
     * @return
     */
    @GetMapping("/getOneTask")
    public Result getOneTask(@RequestParam Integer taskId) {
        return studentService.getOneTask(taskId, "student/getOneTask");
    }

    /**
     * 做题(不带图片)
     * @param studentId
     * @param topicId
     * @param taskId
     * @param answer
     * @return
     */
    @PostMapping("/makeTakeNoPicture")
    public Result makeTakeNoPicture( @RequestParam Integer studentId,
                                     @RequestParam Integer topicId,
                                     @RequestParam Integer taskId,
                                     @RequestParam String answer) {
        return studentService.makeTakeNoPicture(studentId, topicId, taskId, answer, "student/makeTakeNoPicture");
    }

    /**
     * 做题(带图片)
     * @param studentId
     * @param topicId
     * @param taskId
     * @param answerPicture
     * @return
     * @throws IOException
     */
    @PostMapping("/makeTakeWithPicture")
    public Result makeTakeWithPicture(@RequestParam Integer studentId,
                                      @RequestParam Integer topicId,
                                      @RequestParam Integer taskId,
                                      @RequestParam MultipartFile answerPicture) throws IOException {
        return studentService.makeTakeWithPicture(studentId, topicId, taskId, answerPicture, "student/makeTakeWithPicture");
    }

    /**
     * 添加评论
     * @param commentDto
     * @return
     */
    @PostMapping("/addComment")
    public Result addComment(@RequestBody CommentDto commentDto) {
        return studentService.addComment(
                commentDto.getCmTopicId(),
                commentDto.getCmUserId(),
                commentDto.getCmUserRole(),
                commentDto.getCmContent(), "student/addComment");

    }
}