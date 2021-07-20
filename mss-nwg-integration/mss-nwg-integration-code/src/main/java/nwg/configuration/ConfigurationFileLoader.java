package nwg.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.File;

@Configuration
public class ConfigurationFileLoader {

  private static final Logger LOGGER = LogManager.getLogger(ConfigurationFileLoader.class);

  public static final String ENVIRONMENT_VARIABLE = "mss.boi.integration.config";
  private static final String MESSAGE_CONFIGURATION_FILE_LOADED = "Configuration file loaded - %s.";
  private static final String MESSAGE_ENVIRONMENT_VARIABLE_NOT_SET = "Environment variable " + ENVIRONMENT_VARIABLE
      + " has not been set.";
  private static final String MESSAGE_CONFIGURATION_FILE_NOT_FOUND = "Configuration file %s not found.";

  @Autowired
  private Environment environment;

  public File loadFile(String fileName) {
    LOGGER.traceEntry();
    String filePath = environment.getProperty(ENVIRONMENT_VARIABLE);
      System.out.println(filePath);
    if (filePath == null) {
      LOGGER.warn(MESSAGE_ENVIRONMENT_VARIABLE_NOT_SET);
      return null;
    }
    String fileFullPath = filePath + File.separator + fileName;
    File file = new File(fileFullPath);
    if (!(file.exists())) {
      String message = String.format(MESSAGE_CONFIGURATION_FILE_NOT_FOUND, fileFullPath);
      LOGGER.warn(message);
      return null;
    }
    LOGGER.info(String.format(MESSAGE_CONFIGURATION_FILE_LOADED, fileFullPath));
    return LOGGER.traceExit(file);
  }

  void setEnvironment(Environment environment) {
    this.environment = environment;
  }

}
