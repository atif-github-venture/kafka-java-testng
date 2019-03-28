package api.classes;

import enums.FileRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import web.utilities.PropertiesUtil;

import java.util.Optional;

public class DeleteServices extends WebServices {

	private static Logger log = LogManager.getLogger(DeleteServices.class.getName());
	private static DeleteServices deleteService;
	private static RestTemplate restCall;

	protected DeleteServices() {
	}

	/**
	 * <h1>This method is used to delete the response.</h1>
	 * <p>
	 * @param headers This parameter accepts headers
	 * @param uri This parameter accepts uri
	 * <b>Example:</b> getInstance().deleteData( headers, uri);
	 */
	public ResponseEntity<String> deleteData(Optional<HttpHeaders> headers, String uri) {
		return headers.map(httpHeaders -> restCall
				.exchange(uri, HttpMethod.DELETE, new HttpEntity<>(httpHeaders), String.class))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	/**
	 * <h1>This method is used to delete the response.</h1>
     * <p>
	 * @param headers This parameter accepts headers
	 * @param uri This parameter accepts uri
	 * @param jsonBody This parameter accepts jsonBody
	 * <b>Example:</b> getInstance().deleteData( headers, uri, jsonBody);
	 */
	public ResponseEntity<String> deleteData(Optional<HttpHeaders> headers, String uri, Object jsonBody) {
		return headers.map(httpHeaders -> restCall
				.exchange(uri, HttpMethod.DELETE, new HttpEntity<>(jsonBody, httpHeaders), String.class))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	/**
	 *
	 * @return singleton for this class
	 */
	public static synchronized DeleteServices getInstance() {
		return Optional.ofNullable(deleteService).orElseGet(() -> {
			deleteService = new DeleteServices();
			restCall = new RestTemplate();
			//log.info("Loading GET service YAML props");
			PropertiesUtil.loadYamlProperties(FileRepository.DELPROPS);
			return deleteService;
		});
	}
}
