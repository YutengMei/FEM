package com.ym.mall.portal.config;

import com.ym.mall.common.config.BaseSwaggerConfig;
import com.ym.mall.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 * Created by macro on 2018/4/26.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.ym.mall.modules")
                .title("front end")
                .description("front end")
                .contactName("ym")
                .version("1.0")
                .enableSecurity(false)
                .build();
    }
}
