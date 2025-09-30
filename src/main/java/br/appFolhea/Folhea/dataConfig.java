package br.appFolhea.Folhea;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
@Configuration
public class dataConfig {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // Bd temporário
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"); // Banco H2 em memória
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        //dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //dataSource.setUrl("jdbc:mysql://localhost:3306/my_database?useTimezone=true&serverTimezone=UTC");
        //dataSource.setUsername("myusername");
        //dataSource.setPassword("mypassword");
        return  dataSource;
    }
    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        // Bd temporário
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);

        //adapter.setDatabase(Database.MYSQL);
        //adapter.setShowSql( true);
        //adapter.setGenerateDdl(true);
        //adapter.setDatabasePlatform("org.hibernate.dialect.MariaDBDialect");
        return adapter;

    }
}
