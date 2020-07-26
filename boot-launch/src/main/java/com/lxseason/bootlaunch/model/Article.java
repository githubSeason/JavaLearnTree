package com.lxseason.bootlaunch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   //给实体类加上get 、set、toString、equals、hashCode这方法
@Builder //构造新的对象
@AllArgsConstructor     //lombok插件提供的这个注解可以提供所有参数的构造函数，避免写构造函数
@NoArgsConstructor
public class Article {
    private Long id;
    private String author;
}
