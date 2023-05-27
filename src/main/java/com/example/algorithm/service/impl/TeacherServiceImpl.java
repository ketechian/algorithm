package com.example.algorithm.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.io.FileTypeUtil;
import com.example.algorithm.domain.bo.InputStudentBo;
import com.example.algorithm.domain.po.*;
import com.example.algorithm.domain.po.Class;
import com.example.algorithm.domain.vo.ClassVo;
import com.example.algorithm.domain.vo.TeacherVo;
import com.example.algorithm.mapper.*;
import com.example.algorithm.service.TeacherService;
import com.example.algorithm.utils.PageBean;
import com.example.algorithm.utils.Result;
import com.example.algorithm.utils.UUIDUtil;
import com.example.algorithm.utils.oss.OssFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 19:38
 */

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    OssFileUtil ossFileUtil;

    @Autowired
    PictureMapper pictureMapper;

    @Autowired
    ClassMapper classMapper;

    @Autowired
    TeacherClassMapper teacherClassMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    StudentClassMapper studentClassMapper;

    @Autowired
    StudentAnswerMapper studentAnswerMapper;

    @Autowired
    HyperlinksMapper hyperlinksMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    TopicMapper topicMapper;

    @Autowired
    InformationGroupMapper informationGroupMapper;

    @Autowired
    InformationMapper informationMapper;

    @Autowired
    TaskGroupMapper taskGroupMapper;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    ExamineMapper examineMapper;

    /**
     * 创建班级
     */
    @Override
    public Result createClass(String className, String sOpenTime, String sEndTime, String classStatus, Integer teacherId,
                              String introduce, MultipartFile headPicture, String path) throws ParseException, IOException {
        Date openTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sOpenTime);
        Date enfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sEndTime);

        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        //生成uuid
        String uuid = UUIDUtil.getUUID();
        List<String> pathList = new ArrayList<>();
        pathList.add("class");
        pathList.add(className);
        pathList.add(uuid);

        //上传图片, 添加路径表, 添加图片表
        boolean uploadPicture = ossFileUtil.uploadPicture(headPicture, pathList);

        try {
            if (uploadPicture) {
                //添加班级表
                Picture picture = pictureMapper.selectByName(uuid);
                Class aClass = classMapper.selectByName(className);
                if (aClass == null) {
                    Class aClass1 = new Class(className, openTime, enfTime, classStatus, introduce, picture.getPtId());
                    classMapper.insert(aClass1);

                    //添加老师班级关系表
                    teacherClassMapper.insert(new TeacherClass(aClass1.getClId(), teacherId));

                    createCode = 1;
                    data.put("createCode", createCode);
                    data.put("result", "添加成功");
                } else {
                    data.put("createCode", createCode);
                    data.put("result", "班级已存在");
                }
            } else {
                data.put("createCode", createCode);
                data.put("result", "添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            message.put("register_code", createCode);
            message.put("data", data);
        }
        return new Result().result200(message, path);
    }

    /**
     * 导入学生
     */
    @Override
    public Result inputStudent(Integer classId, MultipartFile studentExcel, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();


        String filename = studentExcel.getOriginalFilename();
        String fileType = filename.substring(filename.lastIndexOf("."));

        //先判断后缀名
        if (".xls".equalsIgnoreCase(fileType) || ".xlsx".equalsIgnoreCase(fileType)) {
            String type;
            try {
                type = FileTypeUtil.getType(studentExcel.getInputStream());
                //根据首部字节判断文件类型
                if (".xls".contains(type) || ".xlsx".contains(type) || ".zip".contains(type)) {
                    ImportParams params = new ImportParams();
                    params.setTitleRows(1);
                    params.setHeadRows(1);

                    List<InputStudentBo> list = ExcelImportUtil.importExcel(studentExcel.getInputStream(),
                            InputStudentBo.class, params);
                    for (InputStudentBo ise : list) {
                        String password = ise.getPhone().substring(5);
                        Student student1 = new Student(ise.getNumber(), ise.getName(), ise.getPhone(),
                                password, ise.getMailbox(), 1);
                        studentMapper.insert(student1);

                        studentClassMapper.insert(new StudentClass(classId, student1.getStId()));
                    }

                    createCode = 1;
                    data.put("createCode", createCode);
                    data.put("result", "添加成功");

                }
            } catch (Exception e) {
                e.printStackTrace();
                data.put("createCode", createCode);
                data.put("result", "添加失败");

            } finally {
                message.put("register_code", createCode);
                message.put("data", data);
            }
        }
        return new Result().result200(message, path);
    }

    /**
     * 添加有图片的题目
     */
    @Override
    public Result addTopicWithPicture(String topicName, Integer type, String topicText, String option, String answer,
                                      MultipartFile topicPicture, Integer hyperlinkId, Integer teacherId, String path) throws IOException {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();
        try {
            boolean uploadPicture = false;
            //生成uuid
            String uuid = UUIDUtil.getUUID();
            List<String> pathList = new ArrayList<>();
            pathList.add("topic");
            pathList.add(uuid);

            //上传图片, 添加路径表, 添加图片表
            uploadPicture = ossFileUtil.uploadPicture(topicPicture, pathList);

            if (uploadPicture) {
                Picture picture = pictureMapper.selectByName(uuid);
                Integer pictureId = picture.getPtId();

                topicMapper.insert(new Topic(topicName, type, pictureId, hyperlinkId, topicText,
                        option, answer, "", teacherId));

                createCode = 1;
                data.put("createCode", createCode);
                data.put("result", "添加成功");
            } else {
                //图片上传失败
                data.put("createCode", createCode);
                data.put("result", "添加失败");
            }
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
     * 添加超链接
     */
    @Override
    public Result addHyperlink(String hyperlink, String information, Integer userId, Integer roleId, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        Integer hlId = null;
        try {
            Hyperlinks isHyperlink = hyperlinksMapper.selectBytText(hyperlink);
            hlId = 0;
            if (isHyperlink == null) {
                if (roleId == 1) {
                    hyperlinksMapper.insert(new Hyperlinks(hyperlink, information, 1, 1, userId, 0));
                    //提交审核,之后写
                } else if (roleId == 2) {
                    hlId = hyperlinksMapper.insert(new Hyperlinks(hyperlink, information, 3, 2, userId, userId));
                }
            } else {
                hlId = isHyperlink.getHlId();
            }
            createCode = 1;
            data.put("createCode", createCode);
            data.put("result", "添加成功");
            data.put("hyperlinkId", hlId);
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
     * 添加没有图片的题目
     */
    @Override
    public Result addTopicNoPicture(String topicName, Integer type, String topicText, String option, String answer,
                                    Integer hyperlinkId, Integer teacherId, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            topicMapper.insert(new Topic(topicName, type, 0, hyperlinkId, topicText,
                    option, answer, "", teacherId));
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
     * 发布消息
     */
    @Override
    public Result addInformation(String informationContent, Integer informationForId, Integer informationFromClass,
                                 Integer informationFromAll, Integer roleId, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            if (informationFromAll == 2) {
                if (roleId == 2) {
                    InformationGroup informationGroup = new InformationGroup(informationContent, informationForId,
                            roleId, informationFromAll, informationFromClass);
                    informationGroupMapper.insert(informationGroup);

                    List<Integer> studentList = studentClassMapper.selectByClass(informationFromClass);
                    if (studentList.size() > 0) {
                        informationMapper.insertGroup(studentList, informationGroup.getIgId());
                        createCode = 1;
                        data.put("createCode", createCode);
                        data.put("result", "添加成功");
                    } else {
                        data.put("createCode", createCode);
                        data.put("result", "添加失败,班级不存在或学生为零");
                    }
                } else {
                    data.put("createCode", createCode);
                    data.put("result", "添加失败,权限错误");
                }
            } else if (informationFromAll == 1) {

                data.put("createCode", createCode);
                data.put("result", "添加失败,权限或参数错误");
            }
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
     * 发布任务
     */
    @Override
    public Result addTask(String tgTopicId, Integer tgForId, Integer tgFromClass, String taskIntroduction, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            int shouldStudent = studentClassMapper.countByClassId(tgFromClass);


            TaskGroup taskGroup = new TaskGroup(tgTopicId, tgForId, tgFromClass, shouldStudent, 0);
            taskGroupMapper.insert(taskGroup);

            List<Integer> studentList = studentClassMapper.selectByClass(tgFromClass);

            String[] split = tgTopicId.split("/");
            for (int i = 0; i < split.length; i++) {
                int topicId = Integer.parseInt(split[i]);
                taskMapper.insertGroup(taskGroup.getTgId(), studentList, topicId);
            }

            addInformation(taskIntroduction, tgForId, tgFromClass, 2, 2, path);

            createCode = 1;
            data.put("createCode", createCode);
            data.put("result", "添加成功");

        } catch (NumberFormatException e) {
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
     * 获取老师个人信息
     */
    @Override
    public Result getInformation(Integer teacherId, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            TeacherVo teacher = teacherMapper.selectByPathNameId(teacherId);
            Integer tPictureId = teacher.getTPictureId();
            Picture picture = pictureMapper.selectByPrimaryKey(tPictureId);
            String fullPath = ossFileUtil.getFullPath(picture.getPtName());
            teacher.setTPicturePath(fullPath);
            createCode = 1;
            data.put("createCode", createCode);
            data.put("result", teacher);
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
     * 修改老师信息
     */
    @Override
    public Result updateInformation(Integer teacherId, String name, Integer gender, Integer age, String phone,
                                    String qq, String wx, String mailbox, String introduce, MultipartFile headPicture,
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

                teacherMapper.updateByPrimaryKey(new Teacher(teacherId, name, gender, age, phone, qq, wx,
                        mailbox, introduce, pictureId));
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
    public Result getClassByTeacher(Integer teacherId, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            List<Integer> classIdList = teacherClassMapper.selectClassByTeacherId(teacherId);

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
    public Result getStudentByClass(Integer teacherId, Integer classId, Integer page, Integer count, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            TeacherClass isTeacherInClass = teacherClassMapper.isTeacherInClass(teacherId, classId);

            if (isTeacherInClass != null) {
                int studentCount = studentClassMapper.countByClassId(classId);
                List<Integer> studentId = studentClassMapper.selectStudentId(classId);
                Integer totalCount = studentCount;
                Integer totalPage = PageBean.getTotalPage(count, totalCount);
                Integer start = (page - 1) * count;
                List<Student> studentData = studentMapper.selectById(studentId, start, count);
                PageBean<Student> studentPage = new PageBean<>(totalCount, page, totalPage, start, studentData);

                createCode = 1;
                data.put("createCode", createCode);
                data.put("result", studentPage);
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
    public Result getMessageByTeacherId(Integer teacherId, Integer roleId, Integer page, Integer count, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();


        try {
            if (roleId == 2) {
                Teacher teacher = teacherMapper.selectByPrimaryKey(teacherId);
                if (teacher != null) {
                    Integer totalCount = informationMapper.countById(teacherId, roleId);
                    Integer totalPage = PageBean.getTotalPage(count, totalCount);
                    Integer start = (page - 1) * count;
                    List<Information> massageList = informationMapper.selectByFromId(teacherId, start, count);
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
    public Result getAllExamine(Integer teacherId, Integer page, Integer count, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            Teacher teacher = teacherMapper.selectByPrimaryKey(teacherId);
            if (teacher != null) {
                Integer totalCount = examineMapper.countAllByTeacherId(teacherId);
                Integer totalPage = PageBean.getTotalPage(count, totalCount);
                Integer start = (page - 1) * count;
                List<Examine> examineList = examineMapper.selectByFromId(teacherId, start, count);
                PageBean<Examine> studentList = new PageBean<>(totalCount, page, totalPage, start, examineList);

                createCode = 1;
                data.put("createCode", createCode);
                data.put("result", studentList);
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
    public Result getNotProcessedExamine(Integer teacherId, Integer page, Integer count, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            Teacher teacher = teacherMapper.selectByPrimaryKey(teacherId);
            if (teacher != null) {
                Integer totalCount = examineMapper.countNotProcessedByTeacherId(teacherId);
                Integer totalPage = PageBean.getTotalPage(count, totalCount);
                Integer start = (page - 1) * count;
                List<Examine> examineList = examineMapper.selectNotProcessedByFromId(teacherId, start, count);
                PageBean<Examine> studentList = new PageBean<>(totalCount, page, totalPage, start, examineList);

                createCode = 1;
                data.put("createCode", createCode);
                data.put("result", studentList);
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
    public Result getReleaseTask(Integer teacherId, Integer page, Integer count, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            Integer totalCount =  taskGroupMapper.countByTeacher(teacherId);
            Integer totalPage = PageBean.getTotalPage(count, totalCount);
            Integer start = (page - 1) * count;
            List<TaskGroup> taskGroupList = taskGroupMapper.selectByTeacherId(teacherId, start, count);
            PageBean<TaskGroup> taskGroupPage = new PageBean<>(totalCount, page, totalPage, start, taskGroupList);

            createCode = 1;
            data.put("createCode", createCode);
            data.put("result", taskGroupPage);
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
    public Result getOneExamine(Integer examineId, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            Examine examine = examineMapper.selectByPrimaryKey(examineId);
            Student student = studentMapper.selectByPrimaryKey(examine.getExForId());
            Topic topic = topicMapper.selectByPrimaryKey(examine.getExTopicId());
            StudentAnswer studentAnswer = studentAnswerMapper.selectByPrimaryKey(examine.getExAnswerId());

            createCode = 1;
            data.put("createCode", createCode);
            data.put("student", student);
            data.put("topic", topic);
            data.put("studentAnswer", studentAnswer);
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
    public Result checkExamine(Integer examineId, String path) {
        int createCode = -1;
        Map<String, Object> data = new Hashtable<>();
        Map<String, Object> message = new HashMap<>();

        try {
            examineMapper.checkByPrimaryKey(examineId);

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
}
