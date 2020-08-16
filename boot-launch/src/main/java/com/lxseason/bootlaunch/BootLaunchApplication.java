package com.lxseason.bootlaunch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * bean自动装配原理
 * 	把全局配置文件(application.properties或application.yml)转化为一个对象，在spring加载过程中使用该对象，根据自定义配置内容（覆盖默认配置值）并影响加载的行为。
 *  @SpringBootApplication注解中自动开启了@EnableAutoConfiguration （允许自动配置）自动配置的动作。
 * 	@EnableAutoConfiguration作用：将类路径META-INF/spring.factories里面配置的所有EnableAutoConfiguration 的值（自动装配类）加入到类执行计划中。
 * 	例：
 *      连按两次Shift"搜索spring.factories，查看spring加载时需要配置自动装配的类（即EnableAutoConfiguration配置下的值）
 * 		快捷键“ctrl+N”，搜寻HttpEncodingAutoConfiguration这个自动配置类，查源码
 * 		HttpEncodingAutoConfiguration自动装配的过程中依赖于HttpEncodingProperties类的自定义属性
 * 	      HttpEncodingProperties的属性在application.yml进行配置，根据这些自定义属性影响spring的加载过程
 * 	      HttpEncodingProperties类的配置对象会被注册到spring.factories中
 */
@SpringBootApplication
@ImportResource(locations = {"classpath:beans.xml"})
public class BootLaunchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootLaunchApplication.class, args);
    }

}
