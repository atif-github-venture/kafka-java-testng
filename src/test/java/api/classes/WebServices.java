package api.classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import web.utilities.PropertiesUtil;

import java.util.Optional;

import static org.springframework.http.HttpHeaders.ORIGIN;

public class WebServices {

	private static Logger log = LogManager.getLogger(WebServices.class.getName());
//	private JVMOptions jvmOptions;

//	public WebServices() {
//		this.jvmOptions = JVMOptions.getInstance();
//	}

    /**
     * <h1>This method is used to get http headers for POST.</h1>
     * <p>
     * <b>Example:</b> getInstance().doGet(getDefaultPostTestHttpHeaders(), bambooUrl.toString(), JiraVisualTestData.class);
     */
	public Optional<HttpHeaders> getDefaultPostTestHttpHeaders() {
		Optional<HttpHeaders> headerObj = Optional.of(new HttpHeaders());
		//headerObj.get().add(ORIGIN, PropertiesUtil.getProperties(jvmOptions.getApplicationName() + "." + ORIGIN.toLowerCase()));
		return headerObj;
	}

	/**
	 * <h1>This method is used to get the auth service uri.</h1>
     * <p>
	 * @param serviceName This parameter accepts name of the service
	 * <b>Example:</b> getInstance().doGet(getAuthServiceUri(serviceName));
	 */
	public static String getAuthServiceUri(String serviceName) {
		log.info("Getting Auth service URI {} ",serviceName);
		return PropertiesUtil.getProperties("api-base-url") + PropertiesUtil
				.getProperties(serviceName + ".base-path") + PropertiesUtil
				.getProperties(serviceName + ".version") + PropertiesUtil
				.getProperties(serviceName + ".service-url");
	}

    /**
     * <h1>This method is used to get the auth service uri.</h1>
     * <p>
     * @param serviceName This parameter accepts service name
     * @param key This parameter accepts key
     * <b>Example:</b> jvmOptions.isFewdBuild() ? jvmOptions.getFewdUrl() : getProperty(jvmOptions.getApplicationName(),FABRIC_BASE_PATH);
     */
	public static String getProperty(String serviceName, String key) {
	//	log.info("Service Name = ", serviceName);
		return PropertiesUtil.getProperties(serviceName + "." + key);
	}

}
