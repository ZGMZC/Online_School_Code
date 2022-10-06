package ossservice.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author ZGMZC
 * @Date 2022/10/6 17:30
 */
/*当项目启动后，spring接口执行接口中的一个方法*/
@Component
public class ConstantPropertiesUtils implements InitializingBean {
    /*读取配置文件内容*/
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    /*定义公开静态常量*/
    public static String End_Point;
    public static String Key_Id;
    public static String Key_Secret;
    public static String Bucket_Name;
    @Override
    public void afterPropertiesSet() throws Exception {
        End_Point=endpoint;
        Key_Id=keyId;
        Key_Secret=keySecret;
        Bucket_Name=bucketName;
    }
}
