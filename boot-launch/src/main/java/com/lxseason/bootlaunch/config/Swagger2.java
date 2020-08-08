package com.lxseason.bootlaunch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 配置Swagger2,向外部发布api接口
 * 优点：使用网页形式根据源码实时发布最新接口，代码变文档跟着变，保证文档的及时性，跨语言性，Swagger UI呈现出一份可交互的API文档
 * 建议有Swagger2的情况下，减少与此对应代码的注释或不写注释
 * 启动项目访问 http://localhost:8080/swagger-ui.html 查询发布的api
 * pom文件增加依赖：
 *          <dependency>
 *             <groupId>io.springfox</groupId>
 *             <artifactId>springfox-swagger2</artifactId>
 *             <version>2.6.1</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>io.springfox</groupId>
 *             <artifactId>springfox-swagger-ui</artifactId>
 *             <version>2.6.1</version>
 *         </dependency>
 * 常用注解：
 * @Api：用在请求的类上，表示对类的说明
 *      tags = "说明该类的作用，可以在UI界面上看到的注解"
 *      value = "该参数没什么意义，在UI界面上也看不到，所以不需要配置"
 * @ApiOperation：用在请求的方法上，说明方法的用途、作用
 *      value ="说明方法的用途，作用"
 *      notes = "方法的备注说明"
 * @ApiResponses：用在请求的方法上，说明一组响应
 *      @ApiResponse:用在@ApiResponses中，一般用于表达一个错误的响应消息
 *          code:数据，如400
 *          message：信息，如"请求参数格式错误"
 *          response：抛出异常的类
 * @ApiImplicitParams
 *      @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
 *          name:参数名
 *          value:参数的汉字说明，解释
 *          required：参数是否必须传
 *          paramType：参数放在哪个地方
 *              header -->请求参数的获取：@RequestHeader
 *              query -->请求参数的获取：@RequestParam
 *              path(用于restful接口) --> 请求参数的获取：@PathVariable
 *              body（不常用）
 *              form（不常用）
 *          dataType：参数类型，默认String，其他值dataType="Integer"
 *          defaultValue:参数的默认值
 * @ApiModel:用于响应类上，表示一个返回响应数据的信息
 *      （这种一般用在post创建的时候，使用@RequestBody这样的场景，
 *      请求参数无法使用@ApiImplicitParam注解进行描述时候）
 *      @ApiModelProperty:用在属性上，描述响应类的属性
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lxseason.bootlaunch"))   //apis配置去哪里扫描controller相关的接口方法
                .paths(PathSelectors.regex("/rest/.*"))     //paths配置哪些接口是对外作为API文档使用swagger发布出去
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("springboot利用swagger构建api文档")
                .description("简单优雅的restful风格")
                .termsOfServiceUrl("http://www.lxseason.com")
                .version("1.0")
                .build();
    }
}
