//package com.aws.epl.demo.config;
//
//
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//
//@Log4j2
//@AllArgsConstructor
//@Component
//public class DataSourceConfig {
//
//	private final DataSourceProperty dataSource;
//	@Primary
//	@Bean
//	DataSource DataSource() {
//		log.info("=================================================================");
//		log.info("Data Source Configuration : {} ", dataSource.toString());
//		log.info("=================================================================");
//		return DataSourceBuilder.create()
//				.url(dataSource.getUrl())
//				.username(dataSource.getUsername())
//				.password(dataSource.getPassword())
//				.driverClassName(dataSource.getDriverClassName())
//				.build();
//	}
//}
