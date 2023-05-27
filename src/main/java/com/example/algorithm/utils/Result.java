package com.example.algorithm.utils;

import java.util.Date;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/14 00:19
 */
public class Result {
    private final Integer STATUS_SUCCESS_CODE=200;
    private final Integer STATUS_ILLEGAL_CODE=401;
    private final Integer STATUS_BAN_CODE=403;
    private final Integer STATUS_NOT_FOUND_CODE=404;
    private final Integer STATUS_ERROR_CODE=500;

    private final String INFO_SUCCESS="SUCCESS:成功";
    private final String INFO_ILLEGAL="ILLEGAL:非法";
    private final String INFO_BAN="BAN:禁止";
    private final String INFO_NOT_FOUND="NOT_FOUND:未找到";
    private final String INFO_ERROR="ERROR:错误";

    private Integer statusCode;
    private String info;
    private Date date;
    private Object message;
    private String path;

    public Result(){}

    public Result(Integer statusCode, String info, Date date, Object message,String path) {
        this.statusCode = statusCode;
        this.info = info;
        this.date = date;
        this.message = message;
        this.path = path;
    }

    public Result result200(Object message,String path){
        return new Result(STATUS_SUCCESS_CODE,INFO_SUCCESS,new Date(),message,path);
    }
    public Result result401(Object message,String path){
        return new Result(STATUS_ILLEGAL_CODE,INFO_ILLEGAL,new Date(),message,path);
    }
    public Result result403(Object message,String path){
        return new Result(STATUS_BAN_CODE,INFO_BAN,new Date(),message,path);
    }
    public Result result404(Object message,String path){
        return new Result(STATUS_NOT_FOUND_CODE,INFO_NOT_FOUND,new Date(),message,path);
    }
    public Result result500(Object message,String path){
        return new Result(STATUS_ERROR_CODE,INFO_ERROR,new Date(),message,path);
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object data) {
        this.message = data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Result{" +
                "statusCode=" + statusCode +
                ", info='" + info + '\'' +
                ", date=" + date +
                ", message=" + message +
                ", path='" + path + '\'' +
                '}';
    }
}
