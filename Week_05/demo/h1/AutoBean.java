package demo.h1;

import org.springframework.stereotype.Component;

@Component
public class AutoBean {
    public AutoBean(){
        System.out.println("构造函数");
    }
    public void info(){
        System.out.println("自动装配");
    }
}