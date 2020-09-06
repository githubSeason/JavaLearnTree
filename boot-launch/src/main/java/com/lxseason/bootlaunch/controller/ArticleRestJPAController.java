package com.lxseason.bootlaunch.controller;

import com.lxseason.bootlaunch.model.AjaxResponse;
import com.lxseason.bootlaunch.model.Article;
import com.lxseason.bootlaunch.model.ArticleVO;
import com.lxseason.bootlaunch.service.ArticleVORestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 */
@Slf4j
@RestController//将类方法的返回结果转为json，等效于在方法的方法前加@ResponseBody + 在类前加@Controller
@RequestMapping("/rest_jpa")    //定义在父页面上的路径
public class ArticleRestJPAController {
    @Resource(name ="articleRestJPAServiceImpl")
    ArticleVORestService articleVORestService;

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
    public @ResponseBody AjaxResponse saveArticle(@RequestBody ArticleVO article){//@RequestBody ,可接收json格式的传入参数，可以传复杂结构的json参数，@ResponseBody可以将结果转为json字符串返回
        articleVORestService.saveArticle(article);
        return AjaxResponse.success(article);
    }

   @DeleteMapping(value ="/article/{id}")
    public AjaxResponse deleteArticle(@PathVariable Long id){//@PathVariable 定义在路径上的变量，/rest/article/1 即id =1
        articleVORestService.deleteArticle(id);
        return AjaxResponse.success(id);
    }

   @PutMapping(value ="/article/{id}" )
    public AjaxResponse updateArticle(@PathVariable Long id ,@RequestBody ArticleVO article){
        article.setId(id);
        articleVORestService.updateArticle(article);
        return AjaxResponse.success(article);
    }

   @GetMapping(value ="/article/{id}")
    public AjaxResponse getArticle(@PathVariable Long id){
        return AjaxResponse.success(articleVORestService.getArticle(id));
    }

    @GetMapping(value ="/articles")
    public AjaxResponse getAllArticle(){
        return AjaxResponse.success(articleVORestService.getAll());
    }
}
