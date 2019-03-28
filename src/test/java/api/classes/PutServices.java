package api.classes;

//import com.lbrands.etaf.enums.FileRepository;
//import com.lbrands.etaf.utilities.PropertiesUtil;
import enums.FileRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import web.utilities.PropertiesUtil;

import java.util.Optional;

public class PutServices extends WebServices {

    private static Logger log = LogManager.getLogger(PutServices.class.getName());
    private static PutServices putService;

    private PutServices() {
        super();
        log.info("Loading PUT service YAML props");
        PropertiesUtil.loadYamlProperties(FileRepository.PUTPROPS);
    }


    /**
     * @return singleton for this class
     */
    public static synchronized PutServices getInstance() {
        return Optional.ofNullable(putService).orElseGet(() -> {
            putService = new PutServices();
            return putService;
        });
    }
}
