package com.lxseason.bootlaunch.yaml;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试spring加载老的项目依赖的的beans.xml配置文件
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImportXmlResourceTests {
    @Autowired
    private ConfigurableApplicationContext ioc;

    @Test
    public void testBeanxmlLoadService(){
        boolean testBeanService = ioc.containsBean("testBeanService");
        System.out.println(testBeanService);//若启动类上不使用@ImportResource(locations = {"classpath:beans.xml"})指定要加载的beans.xml配置，则会输出false
    }
}
