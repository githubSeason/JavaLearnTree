package com.lxseason.bootlaunch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @JsonIgnore、@JsonInclude、@JsonFormat等这些注解一般加在VO对象上
 * VO和PO的核心区别：VO对象可能会比PO对象范围更大一下，会多一些复杂数据结构，如关联到读者信息：List<Reader>
 */
@Data   //给实体类加上get 、set、toString、equals、hashCode这方法
@Builder //构造新的对象
@AllArgsConstructor     //lombok插件提供的这个注解可以提供所有参数的构造函数，避免写构造函数
@NoArgsConstructor
@JsonPropertyOrder(value={"content","title"})
public class ArticleVO {
    @JsonIgnore
    private Long id;
    //    @JsonProperty("writer")//是Jackson常用注解，给属性取别名，在接收和返回时都用别名
    private String author;
    //    @JsonIgnore//是Jackson常用注解，接收和返回时忽略被注解的属性，排除属性不做序列化
    private String title;
    private String content;
    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "CMT+8")//是Jackson常用注解，指定属性格式。可以在配置中统一对日期格式设置，就不用在日期属性上加注解
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //    @JsonInclude(JsonInclude.Include.NON_NULL)//是Jackson常用注解，排除为空元素不做序列化反序列化
    private List<Reader> reader;
}