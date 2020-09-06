package com.lxseason.bootlaunch.service;

import com.lxseason.bootlaunch.dao.ArticlePO;
import com.lxseason.bootlaunch.dao.ArticleRepositiry;
import com.lxseason.bootlaunch.model.ArticleVO;
import com.lxseason.bootlaunch.utils.DozerUtils;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class ArticleRestJPAServiceImpl implements ArticleVORestService{
    @Resource
    private ArticleRepositiry articleRepositiry;    //jpa提供的数据库操作对象

    @Resource
    private Mapper dozerMapper; //作用：把页面传来的VO转为PO

    public ArticleVO saveArticle(@RequestBody ArticleVO article){
        ArticlePO articlePO = dozerMapper.map(article ,ArticlePO.class);
        articleRepositiry.save(articlePO);
        return article;
    }

//    @Transactional
    @Override
    public void deleteArticle(Long id) {
        articleRepositiry.deleteById(id);
    }

    @Override
    public void updateArticle(ArticleVO articleVO) {
        ArticlePO articlePO = dozerMapper.map(articleVO ,ArticlePO.class);
        articleRepositiry.save(articlePO);
    }

    @Override
    public ArticleVO getArticle(Long id) {
        Optional<ArticlePO> articlePO = articleRepositiry.findById(id);//从数据库把文章查出来
        //从其他表把读者查出来
        ArticleVO articleVO = dozerMapper.map(articlePO.get() ,ArticleVO.class);
//        articleVO.setReader();    //给VO扩展reader
        return articleVO;
    }



    @Override
    public List<ArticleVO> getAll() {
        List<ArticlePO> articlePOList = articleRepositiry.findAll();
        return DozerUtils.mapList(articlePOList ,ArticleVO.class);
    }
}
