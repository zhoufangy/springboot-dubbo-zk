package com.boot.dubbo.config.swagger;

import com.boot.dubbo.utils.StatusResponse;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * Created by ZhouFangyuan on 2018/8/7. Information:
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    @Bean
    public Docket createRestApi() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Dubbo")
                .description("消费者 接口文档")
                .version("1.0.1")
                .contact(new Contact("yuanzi", "", ""))
                .build();
        List<ResponseMessage> msg = Lists.newArrayList();

        for (StatusResponse statusResponse : StatusResponse.values()) {
            msg.add(new ResponseMessageBuilder()
                    .code(statusResponse.val())
                    .message(statusResponse.msg())
                    .responseModel(new ModelRef("Error")).build());
        }

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.boot.dubbo"))
                .paths(PathSelectors.any())
                .build()
                .globalResponseMessage(RequestMethod.GET, msg)
                .globalResponseMessage(RequestMethod.POST, msg)
                .useDefaultResponseMessages(false);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}
