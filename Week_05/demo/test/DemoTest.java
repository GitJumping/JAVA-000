package demo.test;

import demo.h1.AutoBean;
import demo.h1.ConfigBean;
import demo.h1.XmlBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
public class DemoTest {
    @Autowired
    private AutoBean autoExample;
    @Autowired
    private ConfigBean javaConfig;

    @Test
    public void testXml(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("BeanConfig.xml");
        XmlBean xmlExample = (XmlBean) context.getBean("XmlExample");
        xmlExample.info();
    }
}
