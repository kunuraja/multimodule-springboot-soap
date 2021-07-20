package nwg.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
//@EnableJpaRepositories(basePackageClasses = { ExternalServiceAuditRepository.class, TransactDataRepository.class })
//@EntityScan(basePackageClasses = { ExternalServiceAudit.class, TransactData.class })
public class DataSourceConfig {

  /*  @Autowired
    private Environment environment;

    @Autowired
    private EnvironmentConfig environmentConfig;

    @Value("${datasource.jndi.path}")
    private String jndi_name;

    // Note - This probably won't work via Spring Boot.
    @Bean
    public DataSource dataSource() throws NamingException {
        System.out.println(environmentConfig.getLFS());
        return (DataSource) new JndiTemplate().lookup(jndi_name);
    }*/

}
