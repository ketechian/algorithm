package com.example.algorithm.utils.oss;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.IORuntimeException;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.example.algorithm.domain.po.Path;
import com.example.algorithm.domain.po.Picture;
import com.example.algorithm.mapper.PathMapper;
import com.example.algorithm.mapper.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/6/15 00:43
 */
@Component
public class OssFileUtil implements OssFile, ProgressListener {

    @Autowired
    private PathMapper pathMapper;

    @Autowired
    private PictureMapper pictureMapper;

    //读取配置文件的内容
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.keyid}")
    private String accessKeyId;
    @Value("${aliyun.oss.file.keysecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    private long bytesWritten = 0;
    private long totalBytes = -1;
    private boolean succeed = false;

    public String getFullPath(String path) {
        Path lostPathClass = pathMapper.selectByPhName(path);
        int perId = lostPathClass.getPhFatherId();
        int layer =lostPathClass.getPhLayer();

        Picture picture = pictureMapper.selectByName(path);

        String fullPath = path;
        for (int i = 0; i < layer - 1; i++) {
            Path nextPathClass = pathMapper.selectByPrimaryKey(perId);
            String nextPath = nextPathClass.getPhName();
            fullPath = nextPath + "/" + fullPath ;

            perId = nextPathClass.getPhFatherId();
        }
        fullPath = fullPath + "." + picture.getPtType();

        return  fullPath;
    }

    //进度条
    @Override
    public void progressChanged(ProgressEvent progressEvent) {
        long bytes = progressEvent.getBytes();
        ProgressEventType eventType = progressEvent.getEventType();
        switch (eventType) {
            case TRANSFER_STARTED_EVENT:
                System.out.println("Start to upload......");
                break;
            case REQUEST_CONTENT_LENGTH_EVENT:
                this.totalBytes = bytes;
                System.out.println(this.totalBytes + " bytes in total will be uploaded to OSS");
                break;
            case REQUEST_BYTE_TRANSFER_EVENT:
                this.bytesWritten += bytes;
                if (this.totalBytes != -1) {
                    int percent = (int)(this.bytesWritten * 100.0 / this.totalBytes);
                    System.out.println(bytes + " bytes have been written at this time, upload progress: " + percent +
                            "%(" + this.bytesWritten + "/" + this.totalBytes + ")");
                } else {
                    System.out.println(bytes + " bytes have been written at this time, upload ratio: unknown" +
                            "(" + this.bytesWritten + "/...)");
                }
                break;
            case TRANSFER_COMPLETED_EVENT:
                this.succeed = true;
                System.out.println("Succeed to upload, " + this.bytesWritten + " bytes have been transferred in total");
                break;
            case TRANSFER_FAILED_EVENT:
                System.out.println("Failed to upload, " + this.bytesWritten + " bytes have been transferred");
                break;
            default:
                break;
        }
    }

    /**
     * 判断文件是否存在
     *
     * @param path 空间路径
     * @return boolean
     */
    @Override
    public boolean isStorageSpace(String path) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        boolean found = false;

        String fullPath = getFullPath(path);
        try {
            // 判断文件是否存在。如果返回值为true，则文件存在，否则存储空间或者文件不存在。
            found = ossClient.doesObjectExist(bucketName, fullPath);
            System.out.println(fullPath);
        } catch (OSSException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        } finally {
            // 关闭OSSClient
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return found;
    }

    /**
     * inputStream上传图片
     * @param imgFile
     * @param pathList
     * @return
     */
    @Override
    public boolean uploadPicture(MultipartFile imgFile, List<String> pathList) throws IOException {

        boolean isCreate = false;
        //获取图片名和后缀
        String imgName = imgFile.getOriginalFilename();
        assert imgName != null;
        //获取图片后缀
        String fileType = imgName.substring(imgName.lastIndexOf("."));
        //获取原图片名
        String metaImgName = imgName.substring(0, imgName.lastIndexOf("."));
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //拼接path
        String path = "";
        for (String nextPath :
                pathList) {
            if ("".equals(path)) {
                path = nextPath;
            } else {
                path = path + "/" +nextPath;
            }
        }

        try {
            //双重判断文件类型是否是图片
            if (".jpg".equals(fileType) || ".jpeg".equals(fileType) || ".png".equals(fileType) || ".gif".equals(fileType)) {
                String type = FileTypeUtil.getType(imgFile.getInputStream());
                if (".jpg".contains(type) || ".jpeg".contains(type) || ".png".contains(type) || ".gif".contains(type)) {

                    //最终路径
                    String fullPath = path + "." + type;

                    //上传
                    InputStream inputStream = imgFile.getInputStream();
                    ossClient.putObject(bucketName, fullPath, inputStream);

                    //添加进路径表
                    //路径父id
                    int perId = 0;
                    //当前层数
                    int layer = 0;
                    //最大层数
                    int maxLayer = pathList.size();
                    Path aPath = null;
                    for (int i = 0; i < maxLayer; i++) {
                        aPath = null;
                        layer = i;
                        aPath = pathMapper.selectByPhName(pathList.get(i));
                        if (aPath == null) {
                            pathMapper.insert(new Path(pathList.get(i), perId, layer + 1));
                            aPath = pathMapper.selectByPhName(pathList.get(i));
                        }
                        perId = aPath.getPhId();
                    }

                    //添加图片表
                    pictureMapper.insert(new Picture(metaImgName, pathList.get(pathList.size() - 1), type, perId));

                    isCreate = true;
                }
            }
        } catch (IORuntimeException | IOException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return isCreate;
    }
}
