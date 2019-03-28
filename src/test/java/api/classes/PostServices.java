package api.classes;

import enums.FileRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import web.utilities.PropertiesUtil;

import java.util.Optional;

public class PostServices extends WebServices {

    private static Logger log = LogManager.getLogger(PostServices.class.getName());
    private static PostServices postService;
    private static RestTemplate restCall;

    protected PostServices() {
        super();
    }

    /**
     * <h1>This method is used to Post the response.</h1>
     * <p>
     * <b>Example:</b> getInstance().doPost(new HttpHeaders(), jiraServiceApi, obj);
     * @param headers This parameter accepts headers
     * @param uri This parameter accepts uri
     * @param jsonBody This parameter accepts json body
     */
    public ResponseEntity<Object> doPost(HttpHeaders headers, String uri, Object jsonBody) {
        try {
            return restCall.postForEntity(uri, new HttpEntity<>(jsonBody, headers), Object.class);
        } catch (RestClientException e) {
            log.error("Error while post Request " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(StringUtils.EMPTY);
        }
    }

    /**
     * @return singleton for this class
     */
    public static synchronized PostServices getInstance() {
        return Optional.ofNullable(postService).orElseGet(() -> {
            postService = new PostServices();
            restCall = new RestTemplate();
            log.info("Loading POST service YAML props");
            PropertiesUtil.loadYamlProperties(FileRepository.POSTPROPS);
            return postService;
        });
    }

    /**
     * <h1>This method is used to get the API Uri.</h1>
     * <p>
     * <b>Example:</b> getInstance().getAPIUri(jvmOptions.getApplicationName(), jira_update_report, GlobalVariables.JIRA_API_URI_KEY);
     * @param applicationName This parameter accepts application name
     * @param serviceName This parameter accepts name of the service
     * @param key This parameter accepts URI key
     */
    public String getAPIUri(String applicationName, String serviceName, String key) {
        log.info("Getting getAPIUri serviceName={}, key={} ", applicationName, key);
        return PropertiesUtil.getProperties(applicationName + "." + key) + PropertiesUtil.getProperties(serviceName + ".base-path") + PropertiesUtil  .getProperties(serviceName + ".service-url");
    }


}
