package com.lxseason.bootlaunch.controller;

import com.lxseason.bootlaunch.model.AjaxResponse;
import com.lxseason.bootlaunch.model.Article;
import com.lxseason.bootlaunch.service.ArticleRestService;
import com.lxseason.bootlaunch.service.ArticleRestTestService;
import io.swagger.annotations.*;
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
public class ArticleRestJDBCController {
    @Resource(name ="articleRestJDBCServiceImpl")
    ArticleRestService articleRestService;

    /**
     * 对swagger发布的Api接口进行配置描述
     */
    @ApiOperation(value = "添加文章" ,notes = "添加新的文章" ,tags = "Article" ,httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "成功" ,response = AjaxResponse.class),
            @ApiResponse(code = 400 ,message = "用户输入错误" ,response = AjaxResponse.class),
            @ApiResponse(code = 500 ,message = "系统内部错误" ,response = AjaxResponse.class)
    })

    @PostMapping(value ="/article")
    public @ResponseBody AjaxResponse saveArticle(@RequestBody Article article){//@RequestBody ,可接收json格式的传入参数，可以传复杂结构的json参数，@ResponseBody可以将结果转为json字符串返回
        articleRestService.saveArticle(article);
        return AjaxResponse.success(article);
    }

   @DeleteMapping(value ="/article/{id}")
    public AjaxResponse deleteArticle(@PathVariable Long id){//@PathVariable 定义在路径上的变量，/rest/article/1 即id =1
        articleRestService.deleteArticle(id);
        return AjaxResponse.success(id);
    }

   @PutMapping(value ="/article/{id}" )
    public AjaxResponse updateArticle(@PathVariable Long id ,@RequestBody Article article){
        article.setId(id);
        articleRestService.updateArticle(article);
        return AjaxResponse.success(article);
    }

   @GetMapping(value ="/article/{id}")
    public AjaxResponse getArticle(@PathVariable Long id){
        return AjaxResponse.success(articleRestService.getArticle(id));
    }

    @GetMapping(value ="/articles")
    public AjaxResponse getAllArticle(){
        return AjaxResponse.success(articleRestService.getAll());
    }
}
