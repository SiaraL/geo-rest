    package pl.siara.geo.rest.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:conf/conf.properties")
public class DbConfiguration {

    @Value("${db.driver}")
    private String dbDriver;
    @Value("${db.url}")
    private String dbUrl;
    @Value("${db.auth.username}")
    private String dbUsername;
    @Value("${db.auth.password}")
    private String dbPassword;

    @Bean("serverDataSource")
    public BasicDataSource dataSource() {
        BasicDataSource dataSink = new BasicDataSource();
        dataSink.setDriverClassName(dbDriver);
        dataSink.setUrl(dbUrl);
        dataSink.setUsername(dbUsername);
        dataSink.setPassword(dbPassword);
        return dataSink;
    }

}
