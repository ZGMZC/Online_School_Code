package eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import commonutils.SchoolExcption;
import eduservice.entity.EduSubject;
import eduservice.entity.excel.SubjectData;
import eduservice.service.EduSubjectService;

/**
 * @Author ZGMZC
 * @Date 2022/10/6 21:17
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    /*SubjectExcelListener 需要手动创建，不能交给spring管理，无法注入其它对象*/
    public EduSubjectService eduSubjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData==null){
            throw new SchoolExcption(20001,"文件数据为空");
        }
        /*一行一行读取数据，第一行为一级分类，第二行为二级分类*/
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        /*添加一级分类*/
        if(existOneSubject==null){
            existOneSubject= new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());/*一级分类名称*/
            eduSubjectService.save(existOneSubject);
        }
        /*添加二级分类*/
        String pid=existOneSubject.getId();
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), pid);
        if(existTwoSubject==null){
            existTwoSubject= new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());/*二级分类名称*/
            eduSubjectService.save(existTwoSubject);
        }
    }

    /*判断一级分类不能重复添加*/
    private EduSubject existOneSubject(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject one = eduSubjectService.getOne(wrapper);
        return one;
    }
    /*判断二级分类不能重复添加*/
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject two = eduSubjectService.getOne(wrapper);
        return two;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
