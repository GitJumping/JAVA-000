package demo.h1;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {
    public ConfigBean javaExample(){
        return new ConfigBean();
    }
}
