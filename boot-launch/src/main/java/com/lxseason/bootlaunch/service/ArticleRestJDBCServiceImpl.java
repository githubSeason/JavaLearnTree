package com.lxseason.bootlaunch.service;

import com.lxseason.bootlaunch.dao.ArticleJDBCDAO;
import com.lxseason.bootlaunch.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ArticleRestJDBCServiceImpl implements ArticleRestService {
    @Resource
    ArticleJDBCDAO articleJDBCDAO;
    public Article saveArticle(@RequestBody Article article){
        articleJDBCDAO.save(article);
        return article;
    }

    @Transactional
    @Override
    public void deleteArticle(Long id) {
        articleJDBCDAO.deleteById(id);
    }

    @Override
    public void updateArticle(Article article) {
        articleJDBCDAO.updateById(article);
    }

    @Override
    public Article getArticle(Long id) {
        return articleJDBCDAO.findById(id);
    }

    @Override
    public List<Article> getAll() {
        return articleJDBCDAO.findAll();
    }
}
