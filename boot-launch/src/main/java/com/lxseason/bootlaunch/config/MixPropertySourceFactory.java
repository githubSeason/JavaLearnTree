package com.lxseason.bootlaunch.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.Properties;

/**
 * 全局配置文件
 * 	application.properties或application.yml
 * 	作用：修改springboot自动配置的默认值，（全局配置文件由springboot框架底层自动加载）
 * 	bean自动装配原理
 * 	把全局配置文件转化为一个对象，在spring加载过程中使用该对象，并影响加载的行为。
 *        @SpringBootApplication注解中自动开启了@EnableAutoConfiguration （允许自动配置）自动配置的动作。
 * 	@EnableAutoConfiguration作用：将类路径META-INF/spring.factories里面配置的所有EnableAutoConfiguration 的值（自动装配类）加入到类执行计划中。
 * 	例：
 * 		快捷键"ctrl+N"，搜寻HttpEncodingAutoConfiguration这个自动配置类
 * 		HttpEncodingAutoConfiguration自动装配的过程中依赖于HttpEncodingProperties类的自定义属性
 * 	    HttpEncodingProperties的属性在application.yml进行配置，根据这些自定义属性影响spring的加载过程
 * 	    HttpEncodingProperties类的配置对象会被注册到spring.factories中
 * 	    "连按两次Shift"搜索spring.factories，查看spring加载时需要配置自动装配的类
 *
 * 有一些老的项目的jar包并未主动去与springboot融合，如果使用这些jar包就得使用它们自己的配置文件
 * 如果我们的应用也要需要这些配置，就不要同样的配置写两份（一份写在application.yml，一份放在自定义的配置文件）
 * 这种还不如多个配置文件就好了，为此需要知道如何加载外部定义配置文件。
 *
 * MixPropertySourceFactory在默认基础上扩展支持yml配置文件的加载（重写createPropertySource），即支持yml配置加载也支持properties配置加载
 * @PropertySource注解：使用在配置类上，可以把yml或properties配置文件读取到项目中来
 *  默认是读取properties文件，但通过factory指定MixPropertySourceFactory.class就可以读取yml
 */
public class MixPropertySourceFactory extends DefaultPropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        String sourceName = name != null ? name : resource.getResource().getFilename();
        if(!resource.getResource().exists()){
            return new PropertiesPropertySource(sourceName ,new Properties());
        }
        else if(sourceName.endsWith(".yml") || sourceName.endsWith(".yaml")){
            Properties propertiesFromYaml = loadYml(resource);
            return new PropertiesPropertySource(sourceName ,propertiesFromYaml);
        }
        else{
            return super.createPropertySource(name ,resource);
        }
    }

    private Properties loadYml(EncodedResource resource){
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource.getResource());
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
