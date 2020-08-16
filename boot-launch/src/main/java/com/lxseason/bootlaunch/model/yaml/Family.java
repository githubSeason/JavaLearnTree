package com.lxseason.bootlaunch.model.yaml;

import com.lxseason.bootlaunch.config.MixPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * 自定义配置demo的配置类
 * 去test目录下的yaml包中执行单元测试，查看效果
 */
@Data
@Component
//在需要校验的属性装配类上加@Validated 注解,（搜JSR303数据校验规则）
@Validated
//@ConfigurationProperties注解把配置文件中family开始的整个数据结构导入,
// 支持：批量注入，复杂结构，松散语法绑定，JSR303数据校验
//不支持：SpEL表达式语言
@ConfigurationProperties(prefix = "family")
//@PropertySource注解用于把外部额外依赖的配置文件读取到项目中来
// 若额外依赖的配置文件为properties配置文件，则只加value参数
@PropertySource(value = {"classpath:family.yml"}, factory = MixPropertySourceFactory.class)
public class Family {
    //@Value注解:把配置文件中的family.family-name属性的值放到familyName字段中,
    // 支持：实现单个属性的注入，SpEL表达式语言
    // 不支持：实现对象或复杂结构属性的注入，不支持松散语法，JSR303数据校验
//    @Value("${family.family-name}")
    @NotEmpty
    private String familyName;
    private Father father;
    private Mother mother;
    private Child child;
}
