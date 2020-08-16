package com.lxseason.bootlaunch.yaml;

import com.lxseason.bootlaunch.model.yaml.Family;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试自定义配置文件加载
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomYamlTest {
    @Autowired
    Family family;
    @Test
    public void familyTest(){
        System.out.println(family.toString());
    }
}
