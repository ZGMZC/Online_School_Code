package commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ZGMZC
 * @Date 2022/10/3 15:33
 */
@Data
@AllArgsConstructor   //生成有参构造方法
@NoArgsConstructor    //生成无参构造方法
public class SchoolExcption extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer Code;

    private String msg;
}
