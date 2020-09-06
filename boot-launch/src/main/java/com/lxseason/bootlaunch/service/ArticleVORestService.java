package com.lxseason.bootlaunch.service;

import com.lxseason.bootlaunch.model.Article;
import com.lxseason.bootlaunch.model.ArticleVO;

import java.util.List;

public interface ArticleVORestService {
    public ArticleVO saveArticle(ArticleVO article);
    public void deleteArticle(Long id);
    public void updateArticle(ArticleVO article);
    public ArticleVO getArticle(Long id);
    public List<ArticleVO> getAll();
}
