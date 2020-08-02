package com.lxseason.bootlaunch.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 资源对象
 */
@Data   //给实体类加上get 、set、toString、equals、hashCode这方法
@Builder //构造新的对象
@AllArgsConstructor     //lombok插件提供的这个注解可以提供所有参数的构造函数，避免写构造函数
@NoArgsConstructor
//@JsonPropertyOrder(value={"content","title"})//是Jackson常用注解，改变json子元素在序列化时的顺序
public class Article {
    private Long id;
//    @JsonProperty("writer")//是Jackson常用注解，给属性取别名，在接收和返回时都用别名
    private String author;
//    @JsonIgnore//是Jackson常用注解，接收和返回时忽略被注解的属性，排除属性不做序列化
    private String title;
    private String content;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "CMT+8")//是Jackson常用注解，指定属性格式。可以在配置中统一对日期格式设置，就不用在日期属性上加注解
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
//    @JsonInclude(JsonInclude.Include.NON_NULL)//是Jackson常用注解，排除为空元素不做序列化反序列化
    private List<Reader> reader;
}
