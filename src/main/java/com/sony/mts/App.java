package com.sony.mts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = "com.sony.mts")
@EnableTransactionManagement // 开启事务（可选项）
// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
