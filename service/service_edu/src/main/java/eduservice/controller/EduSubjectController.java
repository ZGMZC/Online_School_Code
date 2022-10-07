package eduservice.controller;


import commonutils.R;
import eduservice.entity.vo.OneSubject;
import eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author ZGMZC
 * @since 2022-10-06
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;
    /*添加课程*/
    /*
    * 1.获取上传的文件
    * 2.将文件内容读取出来
    * */
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        eduSubjectService.saveSubject(file,eduSubjectService);
        return R.ok();
    }
    /*课程列表 树形结构*/
    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list=eduSubjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }
}

