package eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author ZGMZC
 * @Date 2022/10/2 17:28
 */
@SpringBootApplication
@ComponentScan(basePackages = {"baseservice","eduservice"})

public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
