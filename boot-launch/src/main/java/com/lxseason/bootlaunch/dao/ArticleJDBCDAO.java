package com.lxseason.bootlaunch.dao;

import com.lxseason.bootlaunch.model.AjaxResponse;
import com.lxseason.bootlaunch.model.Article;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 1、添加pom依赖
 *          <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-jdbc</artifactId>
 *         </dependency>
 *         <dependency>
 *             <groupId>com.microsoft.sqlserver</groupId>
 *             <artifactId>sqljdbc41</artifactId>
 *             <version>6.0</version>
 *         </dependency>
 * 2、配置文件中增加数据库连接信息
 *  spring:
 *    datasource:
 *      url: jdbc:sqlserver://127.0.0.1:1433; DatabaseName=test
 *      username: sa
 *      password: sa
 *      driver-class-name: com.microsoft.sqlserver.jdbc.SQLServerDriver
 * 3、先创建dao层对数据库的各种操作的对象ArticleJDBCDAO
 * 4、去service层创建ArticleRestService接口和ArticleRestJDBCServiceImpl，并在ArticleRestJDBCServiceImpl使用ArticleJDBCDAO对象实现各种业务数据操作
 * 5、去controller层创建ArticleRestJDBCController使用ArticleRestJDBCServiceImpl
 */

@Repository  //@Repository是用于标注持久层对象的注解，会把ArticleJDBCDAO注入到spring的上下文环境中
public class ArticleJDBCDAO {
    @Resource
    private JdbcTemplate jdbcTemplate;
    //保存文章
    public void save(Article article){
        //jdbcTemplate.update适合于insert、update和delete操作
        jdbcTemplate.update("INSERT INTO article(author,title,content,createTime) values(?,?,?,?)",
                article.getAuthor(),
                article.getTitle(),
                article.getContent(),
                article.getCreateTime());
    }

    //删除文章
    public void deleteById(Long id){
        //jdbcTemplate.update适合于insert、update和delete操作
        jdbcTemplate.update("DELETE FROM article WHERE id = ?",new Object[]{id});
    }

    //更新文章
    public void updateById(Article article){
        //jdbcTemplate.update适合于insert、update和delete操作
        jdbcTemplate.update("UPDATE article SET author = ?,title = ?,content = ?,createTime = ? WHERE id =?",
                article.getAuthor(),
                article.getTitle(),
                article.getContent(),
                article.getCreateTime(),
                article.getId());
    }

    //根据id查找文章
    public Article findById(Long id){
        //queryForObject用于查询单条记录返回结果
        return (Article) jdbcTemplate.queryForObject("SELECT * FROM article WHERE id = ?",new Object[]{id},new BeanPropertyRowMapper(Article.class));
    }
    //查询所有
    public List<Article> findAll(){
        //query用于查询结果列表
        return (List<Article>) jdbcTemplate.query("SELECT * FROM article",new BeanPropertyRowMapper(Article.class));
    }
}
