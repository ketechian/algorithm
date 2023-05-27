package com.example.algorithm.service.impl;

import com.example.algorithm.domain.po.*;
import com.example.algorithm.domain.vo.ClassVo;
import com.example.algorithm.domain.vo.StudentVo;
import com.example.algorithm.domain.vo.TaskAllVo;
import com.example.algorithm.domain.vo.TopicVo;
import com.example.algorithm.mapper.*;
import com.example.algorithm.service.StudentService;
import com.example.algorithm.utils.PageBean;
import com.example.algorithm.utils.Result;
import com.example.algorithm.utils.UUIDUtil;
import com.example.algorithm.utils.oss.OssFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/18 19:33
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    PictureMapper pictureMapper;

    @Autowired
    OssFileUtil ossFileUtil;

    @Autowired
    StudentClassMapper studentClassMapper;

    @Autowired
    ClassMapper classMapper;

    @Autowired
    InformationMapper informationMapper;

    @Autowired
    InformationGroupMapper informationGroupMapper;

    @Autowired
    StudentAnswerMapper studentAnswerMapper;

    @Autowired
    TopicMapper topicMapper;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    TaskGroupMapper taskGroupMapper;
    
    @Autowired
    ExamineMapper examineMapper;

    @Autowired
    TopicStatisticsMapper topicStatisticsMapper;

    @Autowired
    CommentMapper commentMapper;

    @Override
    public Result addComment(Integer cmTopicId, Integer cmUserId, Integer cmUserRole, String cmContent, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            commentMapper.insert(new Comment(cmTopicId, cmUserId, cmUserRole, 0, cmContent, 0, 0));

            createCode = 1;
            data.put("createCode", createCode);
            data.put("result", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            data.put("createCode", createCode);
            data.put("result", "添加失败");
        } finally {
            message.put("register_code", createCode);
            message.put("data", data);
        }
        return new Result().result200(message, path);
    }

    /**
     * 获取学生信息
     *
     * @param studentId
     * @param path
     * @return
     */
    @Override
    public Result getInformation(Integer studentId, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            StudentVo student = studentMapper.selectByPathNameId(studentId);
            Integer stPictureId = student.getStPictureId();
            Picture picture = pictureMapper.selectByPrimaryKey(stPictureId);
            if (picture != null) {
                String fullPath = ossFileUtil.getFullPath(picture.getPtName());
                student.setStPicturePath(fullPath);
            } else {
                student.setStPicturePath("");
            }
            createCode = 1;
            data.put("createCode", createCode);
            data.put("result", student);
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
    public Result updateInformation(Integer studentId, String studentNum, String name, Integer gender, Integer age,
                                    String phone, String qq, String wx, String mailbox, MultipartFile headPicture,
                                    String path) throws IOException {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        //生成uuid
        String uuid = UUIDUtil.getUUID();
        List<String> pathList = new ArrayList<>();
        pathList.add("teacher");
        pathList.add(uuid);

        //上传图片, 添加路径表, 添加图片表
        boolean uploadPicture = ossFileUtil.uploadPicture(headPicture, pathList);

        try {
            if (uploadPicture) {
                Picture picture = pictureMapper.selectByName(uuid);
                Integer pictureId = picture.getPtId();

                studentMapper.updateByPrimaryKey(new Student(studentId, name, studentNum, gender, age, phone, qq, wx,
                        mailbox, pictureId));
                createCode = 1;
                data.put("createCode", createCode);
                data.put("result", "修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.put("createCode", createCode);
            data.put("result", "修改失败");
        } finally {
            message.put("register_code", createCode);
            message.put("data", data);
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result getClassByStudent(Integer studentId, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            List<Integer> classIdList = studentClassMapper.selectClassByStudentId(studentId);

            List<ClassVo> classList = classMapper.selectByClassId(classIdList);

            for (ClassVo classVo : classList) {
                Picture picture = pictureMapper.selectByPrimaryKey(classVo.getClPictureId());
                String fullPath = ossFileUtil.getFullPath(picture.getPtName());
                classVo.setClPicturePath(fullPath);
            }

            createCode = 1;
            data.put("createCode", createCode);
            data.put("result", classList);
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
    public Result getMessageByStudentId(Integer studentId, Integer roleId, Integer page, Integer count, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            if (roleId == 1) {
                Student student = studentMapper.selectByPrimaryKey(studentId);
                if (student != null) {
                    Integer totalCount = informationMapper.countById(studentId, roleId);
                    Integer totalPage = PageBean.getTotalPage(count, totalCount);
                    Integer start = (page - 1) * count;
                    List<Information> massageList = informationMapper.selectByFromId(studentId, start, count);

                    for (Information information : massageList) {
                        Integer imFatherId = information.getImFatherId();
                        InformationGroup informationGroup = informationGroupMapper.selectByPrimaryKey(imFatherId);
                        information.setImText(informationGroup.getIgContent());
                    }

                    PageBean<Information> studentPage = new PageBean<>(totalCount, page, totalPage, start, massageList);

                    createCode = 1;
                    data.put("createCode", createCode);
                    data.put("result", studentPage);
                } else {
                    data.put("createCode", createCode);
                    data.put("result", "参数错误");
                }
            } else {
                data.put("createCode", createCode);
                data.put("result", "参数错误");
            }
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
    public Result checkMassage(Integer messageId, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            informationMapper.checkByPrimaryKey(messageId);

            createCode = 1;
            data.put("createCode", createCode);
            data.put("result", "查收成功");
        } catch (Exception e) {
            e.printStackTrace();
            data.put("createCode", createCode);
            data.put("result", "查收失败");
        } finally {
            message.put("register_code", createCode);
            message.put("data", data);
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result makeQuestionNoPicture(Integer studentId, Integer topicId, String answer, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            Topic topic = topicMapper.selectByPrimaryKey(topicId);

            Boolean isRight = true;
            if (topic.getTpType() == 1 || topic.getTpType() == 2 || topic.getTpType() == 3 || topic.getTpType() == 6) {
                studentAnswerMapper.insert(new StudentAnswer(studentId, 0, topicId, answer));

                String[] answerSplit = answer.split("/");
                String[] topicSplit = topic.getTpAnswer().split("/");


                if (answerSplit.length == topicSplit.length - 1) {
                    for (int i = 0; i < answerSplit.length; i++) {
                        if (!(answerSplit[i].equals(topicSplit[i]))) {
                            isRight = false;
                        }
                    }
                } else {
                    isRight = false;
                }

                if (isRight) {
                    topicMapper.updateRight(topicId);
                } else {
                    topicMapper.updateError(topicId);
                }

                Topic topiEnd = topicMapper.selectByPrimaryKey(topicId);
                createCode = 1;
                data.put("createCode", createCode);
                data.put("result", isRight);
                data.put("topic", topiEnd);
            } else {
                data.put("createCode", createCode);
                data.put("result", "参数错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.put("createCode", createCode);
            data.put("result", "提交失败");
        } finally {
            message.put("register_code", createCode);
            message.put("data", data);
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result makeQuestionWithPicture(Integer studentId, Integer topicId, MultipartFile answerPicture,
                                          String path) throws IOException {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            Topic topic = topicMapper.selectByPrimaryKey(topicId);

            if (topic.getTpType() == 5) {
                //生成uuid
                String uuid = UUIDUtil.getUUID();
                List<String> pathList = new ArrayList<>();
                pathList.add("student");
                pathList.add(uuid);

                ossFileUtil.uploadPicture(answerPicture, pathList);

                Picture picture = pictureMapper.selectByName(uuid);
                Integer pictureId = picture.getPtId();

                studentAnswerMapper.insert(new StudentAnswer(studentId, 0, topicId, pictureId));

                topicMapper.updateError(topicId);
                Topic topiEnd = topicMapper.selectByPrimaryKey(topicId);
                createCode = 1;
                data.put("createCode", createCode);
                data.put("topic", topiEnd);
            } else {
                data.put("createCode", createCode);
                data.put("result", "参数错误");
            }
        } catch (IOException e) {
            e.printStackTrace();
            data.put("createCode", createCode);
            data.put("result", "提交失败");
        } finally {
            message.put("register_code", createCode);
            message.put("data", data);
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result getMessageById(Integer messageId, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            Information information = informationMapper.selectByPrimaryKey(messageId);
            if (information.getImFatherId() != 0) {
                InformationGroup informationGroup = informationGroupMapper.selectByPrimaryKey(information.getImFatherId());
                information.setImText(informationGroup.getIgContent());
            }
            createCode = 1;
            data.put("createCode", createCode);
            data.put("result", information);
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
    public Result getAllTask(Integer studentId, Integer page, Integer count, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            Student student = studentMapper.selectByPrimaryKey(studentId);
            if (student != null) {
                Integer totalCount = informationMapper.countById(studentId, 1);
                Integer totalPage = PageBean.getTotalPage(count, totalCount);
                Integer start = (page - 1) * count;
                List<TaskAllVo> taskList = taskMapper.selectByFromId(studentId, start, count);

                for (TaskAllVo taskAllvo : taskList) {
                    Topic topic = topicMapper.selectByPrimaryKey(taskAllvo.getTkTopicId());
                    taskAllvo.setTopicName(topic.getTpName());
                }
                PageBean<TaskAllVo> taskPage = new PageBean<>(totalCount, page, totalPage, start, taskList);

                createCode = 1;
                data.put("createCode", createCode);
                data.put("result", taskPage);
            } else {
                data.put("createCode", createCode);
                data.put("result", "参数错误");
            }
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
    public Result checkTask(Integer taskId, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            taskMapper.checkTask(taskId);
            createCode = 1;
            data.put("createCode", createCode);
            data.put("result", "查收成功");
        } catch (Exception e) {
            e.printStackTrace();
            data.put("createCode", createCode);
            data.put("result", "查收失败");
        } finally {
            message.put("register_code", createCode);
            message.put("data", data);
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result getOneTask(Integer taskId, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            Task task = taskMapper.selectByPrimaryKey(taskId);
            Topic topic = null;
            if (task != null) {
                TopicVo topicVo = topicMapper.selectTopic(task.getTkTopicId());
                TopicStatistics topicStatistics = topicStatisticsMapper.select(taskId, task.getTkTopicId());

                createCode = 1;
                data.put("createCode", createCode);
                data.put("task", task);
                data.put("topic", topicVo);

                if(task.getTkOver() == 1) {
                    StudentAnswer studentAnswer = studentAnswerMapper.selectByTaskAndStudentId(taskId, task.getTkFromId());
                    data.put("studentAnswer", studentAnswer);
                    data.put("topicStatistics", topicStatistics);
                }

            } else {
                data.put("createCode", createCode);
                data.put("result", "参数错误");
            }
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
    public Result makeTakeWithPicture(Integer studentId, Integer topicId, Integer taskId, MultipartFile answerPicture, String path) throws IOException {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            Topic topic = topicMapper.selectByPrimaryKey(topicId);
            Task task = taskMapper.selectByPrimaryKey(taskId);
            TaskGroup taskGroup = taskGroupMapper.selectByPrimaryKey(task.getTkFatherId());

            TopicStatistics topicStatistics = new TopicStatistics(taskId, topicId);
            topicStatisticsMapper.insert(topicStatistics);

            if (topic.getTpType() == 5) {

                //生成uuid
                String uuid = UUIDUtil.getUUID();
                List<String> pathList = new ArrayList<>();
                pathList.add("student");
                pathList.add(uuid);

                ossFileUtil.uploadPicture(answerPicture, pathList);

                Picture picture = pictureMapper.selectByName(uuid);
                Integer pictureId = picture.getPtId();

                StudentAnswer studentAnswer = new StudentAnswer(studentId, taskId, topicId, pictureId);
                studentAnswerMapper.insert(studentAnswer);
                examineMapper.insert(new Examine(taskGroup.getTgForId(), task.getTkFromId(), topic.getTpId(),
                        0, studentAnswer.getSaId()));

                topicMapper.updateError(topicId);
                taskMapper.updateOver(task.getTkId());
                topicStatisticsMapper.updateRight(topicStatistics.getTsId());

                Topic topiEnd = topicMapper.selectByPrimaryKey(topicId);
                TopicStatistics topicStatisticsEnd = topicStatisticsMapper.selectByPrimaryKey(topicStatistics.getTsId());

                createCode = 1;
                data.put("createCode", createCode);
                data.put("topic", topiEnd);
                data.put("topicStatistics", topicStatisticsEnd);
            } else {
                data.put("createCode", createCode);
                data.put("result", "参数错误");
            }
        } catch (IOException e) {
            e.printStackTrace();
            data.put("createCode", createCode);
            data.put("result", "提交失败");
        } finally {
            message.put("register_code", createCode);
            message.put("data", data);
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result makeTakeNoPicture(Integer studentId, Integer topicId, Integer taskId, String answer, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            Topic topic = topicMapper.selectByPrimaryKey(topicId);
            Task task = taskMapper.selectByPrimaryKey(taskId);
            TaskGroup taskGroup = taskGroupMapper.selectByPrimaryKey(task.getTkFatherId());

            Boolean isRight = true;
            if (topic.getTpType() == 1 || topic.getTpType() == 2 || topic.getTpType() == 3 || topic.getTpType() == 6) {
                StudentAnswer studentAnswer = new StudentAnswer(studentId, taskId, topicId, answer);
                studentAnswerMapper.insert(studentAnswer);

                TopicStatistics topicStatistics = new TopicStatistics(taskId, topicId);
                topicStatisticsMapper.insert(topicStatistics);

                String[] answerSplit = answer.split("/");
                String[] topicSplit = topic.getTpAnswer().split("/");

                if (topic.getTpType() != 6) {
                    if (answerSplit.length == topicSplit.length - 1) {
                        for (int i = 0; i < answerSplit.length; i++) {
                            if (!(answerSplit[i].equals(topicSplit[i]))) {
                                isRight = false;
                            }
                        }
                    } else {
                        isRight = false;
                    }

                    if (isRight) {
                        topicMapper.updateRight(topicId);
                        topicStatisticsMapper.updateRight(topicStatistics.getTsId());
                    } else {
                        topicMapper.updateError(topicId);
                    }

                    taskMapper.updateOver(task.getTkId());
                    Topic topiEnd = topicMapper.selectByPrimaryKey(topicId);
                    TopicStatistics topicStatisticsEnd = topicStatisticsMapper.selectByPrimaryKey(topicStatistics.getTsId());
                    StudentAnswer studentAnswerEnd = studentAnswerMapper.selectByPrimaryKey(studentAnswer.getSaId());

                    createCode = 1;
                    data.put("createCode", createCode);
                    data.put("result", isRight);
                    data.put("topic", topiEnd);
                    data.put("studentAnswer", studentAnswerEnd);
                    data.put("topicStatistics", topicStatisticsEnd);
                } else {
                    examineMapper.insert(new Examine(taskGroup.getTgForId(), task.getTkFromId(), topic.getTpId(),
                            0, studentAnswer.getSaId()));

                    topicMapper.updateError(topicId);
                    Topic topiEnd = topicMapper.selectByPrimaryKey(topicId);
                    StudentAnswer studentAnswerEnd = studentAnswerMapper.selectByPrimaryKey(studentAnswer.getSaId());
                    createCode = 1;
                    data.put("createCode", createCode);
                    data.put("topic", topiEnd);
                    data.put("studentAnswer", studentAnswerEnd);
                }
            } else {
                data.put("createCode", createCode);
                data.put("result", "参数错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.put("createCode", createCode);
            data.put("result", "提交失败");
        } finally {
            message.put("register_code", createCode);
            message.put("data", data);
        }
        return new Result().result200(message, path);
    }

}
