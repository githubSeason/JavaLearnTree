package com.lxseason.bootlaunch.controller;

import com.lxseason.bootlaunch.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 安装idea插件：在idea的setting->plugins中搜索安装，重启idea
 * 安装lombok插件，扩展更多实用注解，需要在pom.xml中添加dependency
 * 安装GsonFormat插件，在某个实体类的编辑页面，按alt+s,粘贴json格式数据，点format，会自动生成该json相关实体类源码。
 * GsonFormat插件一般用于通过调接口成功的json结果来自动生成实体类，高效开发。
 * 安装Maven Helper插件，安装后再pom.xml页面下方出现dependency analyzer 按钮，可以对依赖进行可视化的查看和分析
 *
 * ctrl+shift+a，搜set，set background image,可以给idea设置背景图片，愉悦开发心情。
 */
@Slf4j
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        Article article = new Article(01L,"lxseason");
        article.setAuthor("lxSeason");
        Article article1 = Article.builder().id(2L).author("LxSeason").build();
        log.info("（@Slf4j 注解增加的logger）一次hello被请求");
        return article1.toString();
    }
}
/**
 * RESTFul风格API的好处
 * 1.看url就知道要什么资源（uri只描述资源，不描述对资源的操作）
 * 2.看http method就知道针对资源干什么（http方法：POST/DELETE/PUT/GET分别对应对资源的增、删、改、查，GET方法不应该改变数据）
 * 3.看http status code 就知道结果如何
 *
 * 规范类程序员的代码开发，为前后端交互减少沟通成本，是“约定大于配置”的体现
 */
