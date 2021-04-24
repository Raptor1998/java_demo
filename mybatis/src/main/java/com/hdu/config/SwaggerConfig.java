package com.hdu.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raptor
 * @description Swagger
 * @date 2021/4/22 22:04
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
        //多组配置
//    @Bean
//    public Docket docket1(Environment environment) {
//        //获取环境
//        Profiles profiles=Profiles.of("dev","test");
//        boolean b = environment.acceptsProfiles(profiles);
////        System.out.println(b);
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
//                .enable(b)
//                .groupName("第二组")
//                .select()
//                // 为当前包路径
//                .apis(RequestHandlerSelectors.basePackage("com.hdu.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }

    @Bean
    public Docket docket(Environment environment) {
        //添加head参数配置start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        //获取环境
        Profiles profiles=Profiles.of("dev","test");
        boolean b = environment.acceptsProfiles(profiles);
//        System.out.println(b);
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(b)
//                .host("116.62.143.127")
                .groupName("第一组")
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.hdu.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    // 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        //作者信息
        Contact contact = new Contact("raptor", "https://raptor1998.top", "xxx@163.com");
        return new  ApiInfo("xxx项目文档",
                "项目描述。。。。。",
                "1.0",
                "https://raptor1998.top", contact, "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

    }


}
