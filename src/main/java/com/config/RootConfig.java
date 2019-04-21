package com.config;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(value="com.*",includeFilters={@Filter(type=FilterType.ANNOTATION, value={Service.class})})
@EnableTransactionManagement
public class RootConfig implements TransactionManagementConfigurer {

	private DataSource dataSource = null;
	/*
	 * (non-Javadoc)
	 * @see org.springframework.transaction.annotation.TransactionManagementConfigurer#annotationDrivenTransactionManager()
	 * 配置数据库
	 */
	@Bean(name="dataSource")
	public DataSource initDataSource(){
		if(dataSource != null){
			return dataSource;
		}
		Properties prop = new Properties();
		prop.setProperty("driverClassName", "com.mysql.jdbc.Driver");
		prop.setProperty("url", "jdbc:mysql://localhost:3306/packet");
		prop.setProperty("username", "root");
		prop.setProperty("password", "981209");
		prop.setProperty("maxActive", "200");
		prop.setProperty("maxIdle", "20");
		prop.setProperty("maxWait", "30000");
		try {
			 dataSource = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSource;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.transaction.annotation.TransactionManagementConfigurer#annotationDrivenTransactionManager()
	 * 配置SqlSessionFactoryBean
	 */
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactoryBean initSqlSessionFactory(){
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(initDataSource());
		Resource resource = new ClassPathResource("mybatis-config.xml");
		sqlSessionFactory.setConfigLocation(resource);
		return sqlSessionFactory;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.transaction.annotation.TransactionManagementConfigurer#annotationDrivenTransactionManager()
	 * 自动扫描mapper接口
	 */
	@Bean
	public MapperScannerConfigurer initMapper(){
		MapperScannerConfigurer msc = new MapperScannerConfigurer();
		msc.setBasePackage("com.*");
		msc.setSqlSessionFactoryBeanName("sqlSessionFactory");
		msc.setAnnotationClass(Repository.class);
		return msc;
	}
	
	/*@Bean(name="redisTemplate")
	public RedisTemplate initRedis(){
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		
	}*/
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.transaction.annotation.TransactionManagementConfigurer#annotationDrivenTransactionManager()
	 * 产生数据库事务
	 */
	@Override
	@Bean(name="annotationDrivenTransactionManager")
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		DataSourceTransactionManager dt = new DataSourceTransactionManager();
		dt.setDataSource(initDataSource());
		return dt;
	}

}
