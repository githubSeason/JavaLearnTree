package com.lxseason.bootlaunch.controller;

import com.lxseason.bootlaunch.model.AjaxResponse;
import com.lxseason.bootlaunch.model.Article;
import com.lxseason.bootlaunch.service.ArticleRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * RESTFul风格API的好处
 * 1.看url就知道要什么资源（uri只描述资源，不描述对资源的操作）
 * 2.看http method就知道针对资源干什么（http方法：POST/DELETE/PUT/GET分别对应对资源的增、删、改、查，GET方法不应该改变数据）
 * 3.看http status code 就知道结果如何
 *
 * 规范类程序员的代码开发，为前后端交互减少沟通成本，是“约定大于配置”的体现
 */
@Slf4j
@RestController//将类方法的返回结果转为json，等效于在方法的方法前加@ResponseBody + 在类前加@Controller
@RequestMapping("/rest")    //定义在父页面上的路径
public class ArticleRestController {
    @Resource
    ArticleRestService articleRestService;
//    @RequestMapping(value ="/article" ,method = RequestMethod.POST,produces = "application/json")
    @PostMapping(value ="/article")//上边@RequestMapping写法的简化版
//    public @ResponseBody AjaxResponse saveArticle(@RequestParam String id,
//                                                  @RequestParam String author){//@RequestParam,只能接收通过表达提交的单个参数，不能接收复杂结构的参数，每个特性都要在形参出声明+注解
    public @ResponseBody AjaxResponse saveArticle(@RequestBody Article article){//@RequestBody ,可接收json格式的传入参数，可以传复杂结构的json参数，@ResponseBody可以将结果转为json字符串返回

        log.info("saveArticle:{}",article);
        return AjaxResponse.success(article);
    }

//    @RequestMapping(value ="/article/{id}" ,method = RequestMethod.DELETE ,produces = "application/json")
    @DeleteMapping(value ="/article/{id}")
    public AjaxResponse deleteArticle(@PathVariable Long id){//@PathVariable 定义在路径上的变量，/rest/article/1 即id =1

        log.info("deleteArticle:{}",id);
        return AjaxResponse.success(id);
    }

//    @RequestMapping(value ="/article/{id}" ,method = RequestMethod.PUT ,produces = "application/json")
    @PutMapping(value ="/article/{id}" )
    public AjaxResponse updateArticle(@PathVariable Long id ,@RequestBody Article article){
        article.setId(id);

        log.info("updateArticle:{}",article);
        return AjaxResponse.success(article);
    }

//    @RequestMapping(value ="/article/{id}" ,method = RequestMethod.GET ,produces = "application/json")
    @GetMapping(value ="/article/{id}")
    public AjaxResponse getArticle(@PathVariable Long id){
        Article article =Article.builder().id(1L).author("season").content("story of seasons").createTime(new Date()).title("title1").build();
        log.info("getArticle:{}",id);
        return AjaxResponse.success(article);
    }
}
