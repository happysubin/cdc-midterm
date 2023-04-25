package com.data.cdc;

import io.debezium.config.Configuration;
import io.debezium.connector.mysql.MySqlConnectorConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CdcApplication {

	public static void main(String[] args) {
		SpringApplication.run(CdcApplication.class, args);
	}

	@Bean
	public Configuration customerConnector() {
		return Configuration.create()
				.with("name", "customer_mysql_connector")
				.with("connector.class", "io.debezium.connector.mysql.MySqlConnector")
				.with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
				.with("offset.storage.file.filename", "/tmp/offsets.dat")
				.with("offset.flush.interval.ms", "60000")
				.with("database.hostname", "localhost")
				.with("database.port", "3306")
				.with("database.user", "root")
				.with("database.password", "1234")
				.with("database.dbname", "source")
				//.with("database.include.list", "source")
				//.with("include.schema.changes", "false")
				.with("database.server.id", "10181")
				.with("database.server.name", "customer_mysql_db_server")
				.with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
				.with("database.history.file.filename", "/tmp/dbhistory.dat")
				.with(MySqlConnectorConfig.DATABASE_INCLUDE_LIST, "source")
				.with(MySqlConnectorConfig.TABLE_INCLUDE_LIST , "source.customer")
				.build();
	}
}
