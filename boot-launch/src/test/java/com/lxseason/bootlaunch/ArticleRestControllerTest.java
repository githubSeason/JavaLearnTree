package com.lxseason.bootlaunch;

import com.lxseason.bootlaunch.controller.ArticleRestController;
import com.lxseason.bootlaunch.controller.HelloController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * 在面向对象程序设计中，真实对象往往很难被触发，模拟对象（mock object）是可控的方式模拟真实对象行为的假的对象
 * 非容器环境下测试
 * 注入的外部依赖实际上全部为null
 */
//@Transactional,可以使单元测试进行事务回滚，以保证数据库表中没有因测试造成的垃圾数据，再就是保证单元测试可以反复执行
// @Transactional,不要在Springboot 集成测试环境中使用，事务是由spring控制，而不是数据库，spring会删除数据表
@Slf4j
@SpringBootTest     //@SpringBootTest 是用来创建Spring的上下文ApplicationContext，保证测试在上下文环境里运行，
// 单独使用@SpringBootTest 不会启动servlet容器，不可以使用@Resource 和@Autowired等进行依赖注入（准确的说是可以使用，但是被注解的bean为null）
public class ArticleRestControllerTest {
//    @Resource
//    HelloController helloController;//注入的外部依赖实际上为null
    private MockMvc mockMvc;
    @Before
    public void setUp(){//构建mockMvc对象，用来模拟网络请求
        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleRestController()).build();
    }
    @Test
    public void saveArticle() throws Exception{
        String article = "{" +
                "\"id\":1," +
                "\"author\":\"testseason\"," +
                "\"title\":\"testtitle\"," +
                "\"content\":\"testcontent\"," +
                "\"createTime\":\"2020-08-02 09:59:00\"," +
                "\"reader\":[{\"mame\":\"reader1\",\"age\":18},{\"mame\":\"reader2\",\"age\":19}]" +
                "}";

        /**
         * perform:执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理
         * andExpect：添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确
         * andDo：添加ResultHandler结果处理器，比如调试打印结果到控制台
         * andReturn：最后返回响应的MvcResult，然后进行自定义验证/进行下一步的异步处理
         */
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST ,"/rest/article")
                .contentType("application/json").content(article))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.author").value("testseason"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.reader[0].age").value(18))
                .andDo(print())
                .andReturn();
        log.info(result.getResponse().getContentAsString());
    }
}
