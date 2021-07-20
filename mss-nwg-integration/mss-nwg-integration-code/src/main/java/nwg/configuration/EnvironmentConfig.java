package nwg.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:C:\\Users\\rakumishra\\Documents\\BOI\\NWG\\log4j2.xml")
public class EnvironmentConfig {

    @Value("${log4j.appender.logFile}")
    private String LFS;

    public String getLFS() {
        return LFS;
    }
/*public static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
  public static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
  public static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";*/

}
