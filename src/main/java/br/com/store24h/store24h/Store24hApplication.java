package br.com.store24h.store24h;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@EnableCaching
@SpringBootApplication
@EnableSpringDataWebSupport
public class Store24hApplication {

	public static void main(String[] args) {
		SpringApplication.run(Store24hApplication.class, args);
	}

}
