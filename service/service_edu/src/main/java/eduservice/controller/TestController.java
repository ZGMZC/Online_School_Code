package eduservice.controller;

import commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ZGMZC
 * @Date 2022/10/5 16:54
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class TestController {
    /**
     * login
     */
    @PostMapping("login")
    public R login(){

        return R.ok().data("token","admin");
    }
    /**
     * info
     */
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","admin").data("name","admin").data("avatar","https://img.zcool.cn/community/01e8fa5965991ba8012193a3195e5a.gif");
    }

}
