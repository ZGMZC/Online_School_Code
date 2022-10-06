package eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import eduservice.entity.EduTeacher;
import eduservice.entity.vo.TeacherQuery;

/**
 * @Author ZGMZC
 * @Date 2022/10/2 17:18
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);
}
