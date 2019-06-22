package com.leyou.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: leyou
 * @Package: com.leyou.upload.service
 * @ClassName: UploadService
 * @Author:
 * @Description:
 * @Date: 2019-04-20 7:26
 * @Version: 1.0
 */
public interface UploadService {
    public String uploadImage(MultipartFile file);
}
