package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(value="com.*",includeFilters={@Filter(type=FilterType.ANNOTATION,value=Controller.class)})
@EnableWebMvc
public class WebConfig {
	/*
	 * 初始化视图解析器
	 */
	@Bean(name="internalResourceViewResolver")
	public ViewResolver init(){
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	/*
	 * 初始化RequestMappingHandlerAdapter
	 */
	@Bean(name="requestMappingHandlerAdapter")
	public HandlerAdapter initAdapter(){
		RequestMappingHandlerAdapter rmhd = new RequestMappingHandlerAdapter();
		MappingJackson2HttpMessageConverter json = new MappingJackson2HttpMessageConverter();
		MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;
		List<MediaType> list = new ArrayList<MediaType>();
		list.add(mediaType);
		json.setSupportedMediaTypes(list);
		rmhd.getMessageConverters().add(json);
		return rmhd;
	}
}
