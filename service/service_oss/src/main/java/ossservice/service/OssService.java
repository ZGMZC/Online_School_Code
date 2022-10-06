package ossservice.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author ZGMZC
 * @Date 2022/10/6 17:40
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
