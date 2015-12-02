package org.currconv.config;
 
import java.util.Properties;
import javax.sql.DataSource;
 
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
 
@Configuration
@EnableTransactionManagement
@ComponentScan({ "org.currconv.dao" })
@PropertySource("classpath:/application.properties")
public class SpringDBConfig {
 
	@Autowired
    private Environment environment;
 
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
 
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "org.currconv.entities" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
     
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        if(isProdEnv()){ 
            dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.mySql_driverClassName"));
            dataSource.setUrl("jdbc:mysql://"+System.getenv("OPENSHIFT_MYSQL_DB_HOST")+":"+System.getenv("OPENSHIFT_MYSQL_DB_PORT")+"/cconv");
            dataSource.setUsername(System.getenv("OPENSHIFT_MYSQL_DB_USERNAME"));
            dataSource.setPassword(System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD"));
        } else { // dev environment
            dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.derby_driverClassName"));
            dataSource.setUrl("jdbc:derby:../localdb/cconv;create=true");
        }
        return dataSource;
    }
     
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        if(isProdEnv()){ 
            properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.mysql_dialect"));
        }else{
            properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.derby_dialect"));
        }
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;        
    }
    
    private boolean isProdEnv(){
        String prodAppName = System.getenv("OPENSHIFT_APP_NAME");
        if(null!=prodAppName){ // we are on an openshift environment
            return true;
        }
        return false;
    }
     
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
 
}