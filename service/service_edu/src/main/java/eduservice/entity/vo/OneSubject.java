package eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ZGMZC
 * @Date 2022/10/7 17:32
 */
@Data
public class OneSubject {
    private String id;
    private String title;

    private List<TwoSubject> children=new ArrayList<>();
}
