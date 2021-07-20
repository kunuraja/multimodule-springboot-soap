package nwg.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

@Configuration
public class LoggingConfig {

  private static final Logger LOGGER = LogManager.getLogger(LoggingConfig.class);

  private static final String FILE_NAME = "log4j2.xml";
  private static final String MESSAGE_ATTEMPTING_TO_RECONFIGURE_LOG4J = "Attempting to reconfigure Log4j"
      + " to use custom configuration.";
  private static final String MESSAGE_LOG4J_RECONFIGURED = "Log4j has been reconfigured to use custom configuration.";
  private static final String MESSAGE_USING_DEFAULT_LOGGING_CONFIGURATION = "Custom logging (log4j)"
      + " configuration cannot be loaded, so default configuration will be used instead.";

  @Autowired
  private ConfigurationFileLoader configurationFileLoader;

  @PostConstruct
  public void initialise() {
    LOGGER.info(String.format(MESSAGE_ATTEMPTING_TO_RECONFIGURE_LOG4J));
    LoggerContext context = (LoggerContext) LogManager.getContext(false);
    File file = configurationFileLoader.loadFile(FILE_NAME);
    if (file == null) {
      LOGGER.warn(MESSAGE_USING_DEFAULT_LOGGING_CONFIGURATION);
      return;
    }
    // Force a reconfiguration of log4j to use this configuration file.
    context.setConfigLocation(file.toURI());
    LOGGER.info(MESSAGE_LOG4J_RECONFIGURED);
  }

  void setConfigurationFileLoader(ConfigurationFileLoader configurationFileLoader) {
    this.configurationFileLoader = configurationFileLoader;
  }

}
