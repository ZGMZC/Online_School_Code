package eduservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import commonutils.R;
import commonutils.ResultCode;
import commonutils.SchoolExcption;
import eduservice.entity.EduTeacher;
import eduservice.entity.vo.TeacherQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import eduservice.service.EduTeacherService;

import java.util.List;

/**
 * @Author ZGMZC
 * @Date 2022/10/2 17:12
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 1.查询讲师表所有数据
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R list(){
        List<EduTeacher> list=eduTeacherService.list(null);
        return R.ok().data("items",list);
    }
    /**
     * 2.逻辑删除讲师的方法
     */
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(@PathVariable String id){
        eduTeacherService.removeById(id);
        return R.ok();
    }

    /**
     * 根据条件进行分页查询
     */
    @ApiOperation(value = "分页讲师列表")
    @PostMapping("{page}/{limit}")
    public R pageQueryList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                    @RequestBody TeacherQuery teacherQuery) {

        Page<EduTeacher> pageParam=new Page<>(page,limit);
        eduTeacherService.pageQuery(pageParam,teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }
    /**
     * 新增讲师
     */
    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if(save)
        return R.ok();
        else return R.error();
    }
    /**
     * 修改讲师
     */
    //1.根据id进行查询
    @ApiOperation(value = "根据id进行查询")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }
    //2. 进行修改
    @ApiOperation(value = "进行修改")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if(b)
            return R.ok();
        else return R.error();
    }
    /**
     * 测试异常处理
     */
    @ApiOperation(value = "测试异常处理")
    @GetMapping("TestException")
    public void TestException(){
        try {
            int i=10/0;
        }catch (Exception e) {
            throw new SchoolExcption(ResultCode.ERROR, "除数为0");
        }
    }
}
