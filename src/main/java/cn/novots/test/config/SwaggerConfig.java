package cn.novots.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * * @author ztzh_gonghl * @date 2019年11月27日
 */
@Slf4j
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * 分组API接口 * @return
	 */
	@Bean
	public Docket restApi() {
		boolean enable = true;
		log.info("swagger-init-enable={}", enable);
		return new Docket(DocumentationType.SWAGGER_2).groupName("后管调用接口")
				.apiInfo(apiInfo("Spring Boot中使用Swagger2构建RESTful APIs", "1.0")).enable(enable)
				.useDefaultResponseMessages(true).forCodeGeneration(false).select()
				.apis(RequestHandlerSelectors.basePackage("cn.novots.test.controller")).paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo(String title, String version) {
		return new ApiInfoBuilder().title(title).description("更多请关注: https://xxx.com")
				.termsOfServiceUrl("https://xxx.com").contact(new Contact("xxx", "https://xxx.com", "xxx@zsmaster.com"))
				.version(version).build();
	}
}
