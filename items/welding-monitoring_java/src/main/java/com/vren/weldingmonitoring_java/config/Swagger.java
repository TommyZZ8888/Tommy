package com.vren.weldingmonitoring_java.config;

/**
 * @author GR
 * time 2023-07-04-16-10
 **/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: Swagger2配置类
 * @date: 2022/2/16 7:52
 */

@Configuration
@EnableOpenApi
public class Swagger {

    /**
     * 配置swagger2核心配置
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30) // 指定api类型为swagger2
                .apiInfo(apiInfo()) // 定义api文档汇总信息
                .select().apis(RequestHandlerSelectors.basePackage("com.vren")) // 指定需要提供文档的Controller类所在的包
                .paths(PathSelectors.any()) // 需要生成文档的接口路径
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("这是接口文档页的标题")
                .contact(new Contact(
                        "name",
                        "http://example.com",
                        "example@qq.com"))
                .description("这是一段关于接口文档的描述信息")
                .version("1.0.1")
                .termsOfServiceUrl("http://example.com")
                .build();
    }


}