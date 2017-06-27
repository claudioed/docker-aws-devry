package product.infra.database;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceProducer {

  @Bean
  public DataSource dataSource() {
    return DataSourceBuilder
        .create()
        .username(System.getenv("PG_USER"))
        .password(System.getenv("PG_PASS"))
        .url("jdbc:postgresql://" + System.getenv("PG_HOST") + ":5432/" + System.getenv("PG_DATABASE"))
        .driverClassName("org.postgresql.Driver")
        .build();
  }

}