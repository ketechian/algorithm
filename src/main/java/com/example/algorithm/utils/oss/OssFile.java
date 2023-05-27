package com.example.algorithm.utils.oss;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/16 13:56
 */
public interface OssFile {
    /**
     * 判断空间是否存在
     *
     * @param path 空间路径
     * @return boolean
     */
    boolean isStorageSpace(String path);

    /**
     * 上传图片
     * @param imgFile
     * @param pathList
     * @return
     */
    boolean uploadPicture(MultipartFile imgFile, List<String> pathList) throws IOException;
}
