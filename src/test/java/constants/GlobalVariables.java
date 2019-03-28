package constants;

//import com.lbrands.etaf.utilities.JVMOptions;

import java.io.File;

/**
 * <h1>This is the class containing all the  Global Variables </h1>
 */

public class GlobalVariables {

	public static String parentWindow;

	public static Object currentPage;

	public static Class<?> pageObjectClass;

	public static final String CURRENT_SCENARIO = "CURRENT_SCENARIO";

	public static final String TEST_TYPE = "TEST_TYPE";

	public static final String DEVICE_TYPE = "DEVICE_TYPE";

	public static final String ACTUAL_IMAGE_PATH = "ACTUAL_IMAGE_PATH";

	public static final String EXPECTED_IMAGE_PATH = "EXPECTED_IMAGE_PATH";

	public static final String EXPECTED_UX_IMAGE_PATH = "EXPECTED_UX_IMAGE_PATH";

	public static final String ACTUAL_UX_IMAGE_PATH = "ACTUAL_UX_IMAGE_PATH";

    public static final String DIFF_IMAGE_PATH = "DIFF_IMAGE_PATH";

	public static final String JIRA_API_URI_KEY = "api-appregtool-env-url";

	public static final String FABRIC_BASE_PATH = "flagship-url";

	public static final String JIRA_BASE_PATH = "jira-url";

//	public static boolean RUN_ON_BAMBOO = JVMOptions.getInstance().getBamboobuildTimeStamp() != null
//			&& JVMOptions.getInstance().getBamboobuildTimeStamp().length() > 1;


	public static final String ALPHABETIC = "alphabetic";

	public static final String ALPHANUMERIC = "alphanumeric";

	public static final String NUMERIC = "numeric";

	public static final String SPECIAL = "special";

	public static final String SCENARIO_OUTLINE = "Scenario-Outline";

	public static final String JIRA_ATTRIBUTES = "jira-attributes";

	public static final String MOCK_DATA_BASE_PATH =
			new File("").getAbsolutePath() + "/src/test/resources/data/static/mockey/";

	public final static String testScreenShotDirectory = "target/screenshots";

	public final static String actualScreenShotDirectory = "target/screenshots/actual";

	public final static String expectedScreenShotDirectory = "target/screenshots/expected";

	public final static String mockScreenShotDirectory = "src/test/resources/data";

	public final static String visualLocatorsDirectory = "./src/test/resources/data/visuallocator";

	public final static String imageScriptDirectory = "./src/test/resources/data/imageloadscript/imageload.js";

	public final static String canaryFileDir = "./src/test/resources/canary.yml";

	public static final String FATE = "FATE";


}
