package web.utilities;

//import com.lbrands.etaf.enums.FileRepository;
import enums.FileRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * <h1>This is a class containing all the helper methods related to Properity .yml file</h1>
 */

public class PropertiesUtil {

	private static Logger log = LogManager.getLogger(PropertiesUtil.class.getName());
	private String dataLocation;
	private static Properties properties;

	private PropertiesUtil(String dataLocation) {
		this.dataLocation = dataLocation;
		loadProperties();
	}

	/**
	 * <h1>loads yamal properties</h1>
	 * <p>
	 * @param dataLocation yamal file name with path
	 */
	private static void create(String dataLocation) {
		new PropertiesUtil(dataLocation);
	}

	/**
	 * <h1>Returns value for the given property key</h1>
	 * <p>
	 * @param name property key
	 * @return property value
	 */
	public static String getProperties(String name) {
		String prop = null;
		try {
			prop = properties.getProperty(name);
		} catch (Exception e) {
			log.error("Exception while execution of Properties", e);
		}
		return prop;
	}

	/**
	 * <h1>Appends yaml properties in the local variable properties</h1>
	 * <p>
	 */
	private void loadProperties() {

		YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
		try {
			PropertySource<?> applicationYamlPropertySource = loader.load(
					"properties", new ClassPathResource(dataLocation), null);
			Map source = ((MapPropertySource) applicationYamlPropertySource).getSource();
			getInstance().putAll(source);
		} catch (IOException e) {
			log.error("env-test file cannot be found.");
		}
	}

	/**
	 * <h1>Loads yaml properties in the local variable properties</h1>
	 * <p>
	 * @param repository  FileRepository
	 */
	public static void loadYamlProperties(FileRepository repository) {
		 create(repository.getValue());
	}


	/**
	 * <h1>Instantiates Properties object on the first call
	 * and stores in the local variable properties </h1>
	 * <p>
	 * @return singleton Properties object for the variable properties
	 */
	private static synchronized Properties getInstance() {
		return Optional.ofNullable(properties).orElseGet(() -> {
			properties = new Properties();
			return properties;
		});
	}
}
