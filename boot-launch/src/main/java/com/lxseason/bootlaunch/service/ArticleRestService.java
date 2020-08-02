package com.lxseason.bootlaunch.service;

import com.lxseason.bootlaunch.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
@Slf4j
@Service
public class ArticleRestService {
    public String saveArticle(@RequestBody Article article){

        log.info("saveArticle:{}",article);
        return "测试service";
    }
}
