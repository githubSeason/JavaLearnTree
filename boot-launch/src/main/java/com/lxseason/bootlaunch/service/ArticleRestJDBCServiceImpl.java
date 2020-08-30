package com.lxseason.bootlaunch.service;

import com.lxseason.bootlaunch.dao.ArticleJDBCMultDSDao;
import com.lxseason.bootlaunch.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ArticleRestJDBCServiceImpl implements ArticleRestService {
    //单数据源操作
    /**
    @Resource
    ArticleJDBCDAO articleJDBCMultDSDao;
    public Article saveArticle(@RequestBody Article article){
    articleJDBCMultDSDao.save(article);
    return article;
    }

    @Transactional
    @Override
    public void deleteArticle(Long id) {
        articleJDBCMultDSDao.deleteById(id);
    }

    @Override
    public void updateArticle(Article article) {
        articleJDBCMultDSDao.updateById(article);
    }

    @Override
    public Article getArticle(Long id) {
        return articleJDBCMultDSDao.findById(id);
    }

    @Override
    public List<Article> getAll() {
        return articleJDBCMultDSDao.findAll();
    }
    **/

    //多数据源操作
    @Resource
    ArticleJDBCMultDSDao articleJDBCMultDSDao;

    @Resource
    JdbcTemplate primaryJdbcTemplate;

    @Resource
    JdbcTemplate secondaryJdbcTemplate;
    //一次保存操作分别向两个库中写数据
    public Article saveArticle(@RequestBody Article article){
        articleJDBCMultDSDao.save(article ,primaryJdbcTemplate);
        articleJDBCMultDSDao.save(article ,secondaryJdbcTemplate);
        return article;
    }

    @Transactional
    @Override
    public void deleteArticle(Long id) {
        articleJDBCMultDSDao.deleteById(id ,primaryJdbcTemplate);
    }

    @Override
    public void updateArticle(Article article) {
        articleJDBCMultDSDao.updateById(article ,primaryJdbcTemplate);
    }

    @Override
    public Article getArticle(Long id) {
        return articleJDBCMultDSDao.findById(id ,primaryJdbcTemplate);
    }

    @Override
    public List<Article> getAll() {
        return articleJDBCMultDSDao.findAll(primaryJdbcTemplate);
    }
}
