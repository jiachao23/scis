package com.jcohy.scis;

import com.jcohy.scis.interception.CommonIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ScisApplication extends WebMvcConfigurerAdapter{

	@Autowired
	private CommonIntercepter commonIntercepter;


	public static void main(String[] args) {
		SpringApplication.run(ScisApplication.class, args);
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(commonIntercepter).addPathPatterns("/admin/**");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/").setViewName("login");

		registry.addViewController("/student/index").setViewName("/student/main");

		registry.addViewController("/teacher/index").setViewName("/teacher/main");

		registry.addViewController("/expert/index").setViewName("/expert/main");

		registry.addViewController("/admin/index").setViewName("/admin/main");

		registry.addViewController("/admin/student/index").setViewName("/admin/student/index");

		registry.addViewController("/admin/teacher/index").setViewName("/admin/teacher/index");

		registry.addViewController("/admin/expert/index").setViewName("/admin/expert/index");

	}
}
