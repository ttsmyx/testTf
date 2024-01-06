package cn.novots.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 
 * @author ttscjr
 *
 */
@EnableSwagger2
@SpringBootApplication
@PropertySources({ @PropertySource("classpath:application.properties") })
@ComponentScan(basePackages = { "cn.novots.test"})
public class ApproveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApproveApplication.class, args);
	}
}
