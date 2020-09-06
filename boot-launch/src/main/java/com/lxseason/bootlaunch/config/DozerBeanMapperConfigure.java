package com.lxseason.bootlaunch.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * PO:Persist Object 持久对象，PO是一个平面，也就PO对象里面只有基本数据类型的属性，与数据库字段是一一对应的
 * B0:Business Object 业务对象，PO被查询进程序之后，需要做一系列业务处理，这些处理过程可能使PO对象的属性逐渐变的复杂，
 *      如，对象属性中出现复杂数据结构数组、对象数组等，这复杂的业务数据对象就是BO
 * VO：View Object 展示层对象，BO最终经过属性的组装生成适合前端展示的数据对象即，VO
 *
 * Dozer工具：能在PO、BO和VO的实体和实体间进行转换的工具，只要建立好映射关系
 *      每个转换不是都要写映射的，默认是根据属性名来匹配的。
 *  例：//entity -> entityVo
 *      EntityVo = entityVo = dozerMapper.map(entity ,entityVo);
 * 注入使用即可
 *      @Autowired
 *      protected Mapper dozerMapper
 *
 * Dozer的pom依赖配置：
 *  <dependency>
 *     <groupId>net.sf.dozer</groupId>
 *     <artifactId>dozer</artifactId>
 *     <version>5.4.0</version>
 *  </dependency>
 */
@Configuration
public class DozerBeanMapperConfigure {
    @Bean
    public DozerBeanMapper mapper(){
        DozerBeanMapper mapper = new DozerBeanMapper();
        return mapper;
    }

}
