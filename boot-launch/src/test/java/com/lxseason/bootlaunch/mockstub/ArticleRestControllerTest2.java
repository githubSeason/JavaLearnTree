package com.lxseason.bootlaunch.mockstub;

import com.lxseason.bootlaunch.controller.ArticleRestController;
import com.lxseason.bootlaunch.service.ArticleRestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * 容器环境下进行测试
 * 测试对象有外部依赖的注入
 */
@Slf4j
@RunWith(SpringRunner.class)//RunWith方法为我们构造了一个Servlet容器运行环境，并指定了用SpringRunner.class去跑，在此环境下测试，
// 然而为什么要构建servlet容器？因为如果测试对象有依赖注入，一定要有一个servlet容器，那么这个servlet容器只有RunWith才能提供。注入MockMvc，而不是自己构建mockMvc对象.
// @RunWith扩大运行环境，可以使用依赖注入把其他依赖都注入到spring容器中来
//@AutoConfigureMockMvc注解，该注解表示MockMvc我来构建你来用，这种写法是为了让测试在Spring容器环境下执行，Spring的容器环境是啥？比如常见的service、Dao都是Spring容器里的bean，都可以使用@Resource和@Autowired来测试。
@AutoConfigureMockMvc
@SpringBootTest
public class ArticleRestControllerTest2 {
    @Resource
    private MockMvc mockMvc;//@Resource把MockMvc依赖注入到启动的spring的容器中来使用
    @Resource
    ArticleRestService articleRestService;//注入自定义的依赖，
//    @Before
//    public void setUp(){//构建mockMvc对象，用来模拟网络请求
//        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleRestController()).build();
//    }

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
