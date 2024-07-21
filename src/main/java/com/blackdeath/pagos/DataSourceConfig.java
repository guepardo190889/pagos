package com.blackdeath.pagos;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 * Clase de configuración para inicializar datos en base de datos
 * automáticamente
 */
@Configuration
public class DataSourceConfig {

	@Autowired
	private DataSource dataSource;

	/**
	 * Bean que permite la inicialización automaica de datos en la base de datos
	 * 
	 * @return
	 */
	@Bean
	DataSourceInitializer dataSourceInitializer() {
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		initializer.setDatabasePopulator(databasePopulator());
		return initializer;
	}

	/**
	 * Clase que proporciona la ubicación del archivo sql con la inicialización de
	 * datos
	 * 
	 * @return
	 */
	private ResourceDatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("scripts/data.sql"));
		return populator;
	}
}
