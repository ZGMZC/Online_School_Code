package ossservice.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ossservice.service.OssService;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static ossservice.utils.ConstantPropertiesUtils.*;

/**
 * @Author ZGMZC
 * @Date 2022/10/6 17:40
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = End_Point;
        String accessKeyId = Key_Id;
        String accessKeySecret = Key_Secret;
        String bucketName = Bucket_Name;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            InputStream inputStream = file.getInputStream();
            // 创建PutObject请求。
            /* 1. bucketname 2.filename 3.inputstream*/
            String fileName = file.getOriginalFilename();
            /*UUID 生成随机值*/
            fileName= UUID.randomUUID().toString()+fileName;
            /*获取当前日期*/
            String date = new DateTime().toString("yyyy/MM/dd/");
            fileName=date+fileName;
            ossClient.putObject(bucketName, fileName, inputStream);
            ossClient.shutdown();
            String url="https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
