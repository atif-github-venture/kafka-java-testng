package api.classes;

import enums.FileRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import web.utilities.PropertiesUtil;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class GetServices extends WebServices{

	private static Logger log = LogManager.getLogger(GetServices.class.getName());
	private static RestTemplate restCall = new RestTemplate();
	private static GetServices getService;

	protected GetServices() {
	}

	/**
	 * <h1>This method is used to get the response with body and headers.</h1>
     * <p>
	 * @param headers This parameter accepts headers
	 * @param uri This parameter accepts uri
     * @param type the type of Class
     * @param <T> Class Type to be returned
     * <b>Example:</b> getInstance().doGet(getDefaultPostTestHttpHeaders(), bambooUrl.toString(), JiraVisualTestData.class);
	 */
	public <T> T doGet(Optional<HttpHeaders> headers, String uri, Class<T> type) {
		log.info("GET call made on ,{} ", uri);
		try {
			AtomicReference<T> getResponseWithBodyAndHeaders = new AtomicReference<>();
			headers.ifPresent(httpHeaders -> {
				ResponseEntity<T> securedResponse = restCall.exchange(uri, HttpMethod.GET, new HttpEntity<>(httpHeaders), type);
				getResponseWithBodyAndHeaders.set(securedResponse.getBody());
			});
			return getResponseWithBodyAndHeaders.get();
		} catch (RestClientException e) {
			return (T) new ResponseEntity<Error>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    /**
     * <h1>This method is used to get the response with body and headers.</h1>
     * <p>
     * @param headers This parameter accepts headers
     * @param uri This parameter accepts uri
     * <b>Example:</b> getInstance().doGet( bambooUrl.toString(), getDefaultPostTestHttpHeaders());
     */
	public String doGet(String uri,HttpHeaders... headers) {
		try {
			ResponseEntity<String> securedResponse = restCall
					.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), String.class);
			return securedResponse.getBody();
		} catch (RestClientException e) {
			return String.valueOf(new ResponseEntity<Error>(HttpStatus.INTERNAL_SERVER_ERROR));
		}
	}


	/**
	 *
	 * @return singleton for this class
	 */
	public static synchronized GetServices getInstance() {
		return Optional.ofNullable(getService).orElseGet(() -> {
			getService = new GetServices();
			restCall = new RestTemplate();
			//log.info("Loading GET service YAML props");
			PropertiesUtil.loadYamlProperties(FileRepository.GETPROPS);
			return getService;
		});
	}

    /**
     * <h1>This method is used to get the API URI.</h1>
     * <p>
     * <b>Example:</b> getInstance().getAPIUri(jvmOptions.getApplicationName(), JIRA_SERVICE_NAME, GlobalVariables.JIRA_API_URI_KEY);
     * @param applicationName This parameter accepts application name
     * @param serviceName This parameter accepts name of the service
     * @param key This parameter accepts URI key
     */
	public String getAPIUri(String applicationName, String serviceName, String key) {
		log.info("Getting getAPIUri serviceName={}, key={} ", applicationName, key);

		return PropertiesUtil.getProperties(applicationName + "." + key) + PropertiesUtil.getProperties(serviceName + ".base-path") + PropertiesUtil  .getProperties(serviceName + ".service-url");
	}

    /**
     * <h1>This method is used to get the API URI.</h1>
     * <p>
     * <b>Example:</b> getInstance().getMockServiceUri(serviceName);
     * @param serviceName This parameter accepts name of the service
     */
	public String getMockServiceUri(String serviceName) {
		log.info("Getting Mockey serviceName={}", serviceName);

		return PropertiesUtil.getProperties(serviceName + ".base-path") + PropertiesUtil  .getProperties(serviceName + ".service-url");
	}

}
