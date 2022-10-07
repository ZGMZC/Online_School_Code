package eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import eduservice.entity.EduSubject;
import eduservice.entity.excel.SubjectData;
import eduservice.entity.vo.OneSubject;
import eduservice.entity.vo.TwoSubject;
import eduservice.listener.SubjectExcelListener;
import eduservice.mapper.EduSubjectMapper;
import eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author ZGMZC
 * @since 2022-10-06
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            InputStream in=file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        /*查询出所有的一级分类 */
        QueryWrapper<EduSubject> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("parent_id","0");
        List<EduSubject> eduSubjects = baseMapper.selectList(queryWrapper);

        /*查询出所有的二级分类*/
        QueryWrapper<EduSubject> queryWrapperTwo=new QueryWrapper<>();
        queryWrapperTwo.ne("parent_id","0");
        List<EduSubject> eduSubjectsTwo = baseMapper.selectList(queryWrapperTwo);

        List<OneSubject> res=new ArrayList<>();

        for(EduSubject eduSubject:eduSubjects){
            OneSubject oneSubject=new OneSubject();
/*            oneSubject.setId(eduSubject.getId());
            oneSubject.setTitle(eduSubject.getTitle());*/
            BeanUtils.copyProperties(eduSubject,oneSubject);


            List<TwoSubject> twores=new ArrayList<>();
            for (EduSubject twoSubject : eduSubjectsTwo) {
                if(twoSubject.getParentId().equals(oneSubject.getId())){
                    TwoSubject twoSubject1=new TwoSubject();
                    BeanUtils.copyProperties(twoSubject,twoSubject1);
                    twores.add(twoSubject1);
                }
            }
            oneSubject.setChildren(twores);
            res.add(oneSubject);

        }

        return res;
    }
}
