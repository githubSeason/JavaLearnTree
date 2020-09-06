package com.lxseason.bootlaunch.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @Entity、@Table、@Id、@GeneratedValue、@Column描述数据库的注解等一般加在PO对象上
 * PO基本是一个平面的，和数据库的字段一对一
 * 当第一次连接数据库，发现没有表时，JPA会自动根据实体创建表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity     //标识实体类的注解，jtp会扫描该注解找到实体类
@Table(name = "article")        //指定实体类对应的数据库表
public class ArticlePO {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false,length = 32)
    private String author;

    @Column(nullable = false, unique = true,length = 32)
    private String title;

    @Column(length = 512)
    private String content;


    private Date createTime;
}
