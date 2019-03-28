package web.utilities;


import com.github.javafaker.Faker;
import enums.FileRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static constants.GlobalVariables.*;


/**
 * <h1>This is a class containing all the methods related test data creation using Faker utilities </h1>
 */

public class FakerUtil {

	private static final Logger LOGGER = LogManager.getLogger(FakerUtil.class);


	private static FakerUtil INSTANCE;

	private Faker faker = new Faker();

	public String getFullName() {
		return faker.name().fullName();
	}

	public String getEmail() {
		return faker.internet().emailAddress();
	}

	public String getAddress() {
		return faker.address().fullAddress();
	}

	public String getCompany() {
		return faker.company().name();
	}

	public String getPhone() {
		return faker.phoneNumber().phoneNumber();
	}

	public String getCreditCard() {
		return faker.finance().creditCard();
	}

	public String getSpecialChar() {
		return "@#$%^&*()";
	}

	/**
	 * <h1>This method is used to get random strings of required format</h1>
	 * <b>Example:</b> FakerUtil.getInstance().getInvalidData("email");
	 *
	 * @param type string which determines the String retruned using Faker util
	 * @return invalid random string formed using faker utilities.
	 */
	public String getInvalidData(String type) {
		switch (type.replace(" ", "").toLowerCase()) {
			case "email":
				return faker.internet().emailAddress().replace("@", "");
			case "phone":
				return faker.phoneNumber().phoneNumber() + "@";
			case "streetaddress1":
				return faker.address().streetAddress().replace(" ", "");
			case "zipcode":
				return "00000";
			case "giftcardnumber":
				return faker.number().digits(19);
			case "pin":
				return faker.number().digits(4);
			case "password":
				return getRandomText(ALPHABETIC, "4").toLowerCase()
						+ getRandomText(ALPHABETIC, "4").toUpperCase();
			default:
				throw new Error("Please call for right invalid data type");
		}
	}

	/**
	 * <h1>This method is used to get valid strings of required format</h1>
	 * <b>Example:</b> FakerUtil.getInstance().getValidData("email");
	 *
	 * @param type string which determines the String retruned using Faker util
	 * @return valid random string formed using faker utilities.
	 */
	public String getValidData(String type) {
		switch (type.replace(" ", "").toLowerCase()) {
			case "email":
				return faker.internet().emailAddress();
			case "name":
				return faker.name().fullName();
			case "firstname":
				return faker.name().firstName();
			case "lastname":
				return faker.name().lastName();
			case "address":
				return faker.address().fullAddress();
			case "streetaddress":
				return faker.address().streetAddress();
			case "city":
				return faker.address().city();
			case "zipcode":
				return faker.address().zipCode();
			case "phone":
				String number = faker.phoneNumber().cellPhone();
				number = number.replace("(", "").replace("-", "").replace(".", "").replace("x", "").replace(" ", "")
						.replace(")", "").replace("0", "9").replace("1", "9");
				int len = number.length();
				if (len > 10) {
					number = number.substring(len - 10, len);
				}
				return number;
			case "password":
				return getRandomText(ALPHABETIC, "4").toLowerCase()
						+ getRandomText(ALPHABETIC, "4").toUpperCase()
						+ getRandomText(NUMERIC, "2").toUpperCase();
			case "chinaunioncard":
				return "622555";
			case "creditcardnumber":
				return validCreditCard(faker.finance().creditCard());
			case "vscreditcardnumber":
				return "58563751";
			case "giftcardnumber":
				return "6006496922999918912";
			case "pin":
				return "9656";
			case "offercodenumber":
				return "CHKUAT10item";
			case "editedstreetaddress":
				return "2978 Dublin Arbor Ln";
			case "phonenumber":
				return "6146221234";
			case "areacode":
				return "43017";
			case "last4ssn":
				return "1234";
			case "strongpassword":
				return "FateTest123";
			case "ordernumber":
				return "7654321";
			case "checkoutemailid":
				return "VSVICTORIATWO@GMAIL.COM";
			default:
				throw new Error("Please call for right valid data type");
		}
	}

	/**
	 * <h1>This method is used to get random strings of required format</h1>
	 * <b>Example:</b> getRandomText(ALPHABETIC,"4")
	 *
	 * @param type   string which determines the type of string
	 * @param length length of required format
	 * @return String which contains required format
	 */
	public String getRandomText(String type, String length) {
		switch (type.toLowerCase()) {
			case ALPHABETIC:
				return RandomStringUtils.randomAlphabetic(Integer.valueOf(length));
			case ALPHANUMERIC:
				return RandomStringUtils.randomAlphanumeric(Integer.valueOf(length));
			case NUMERIC:
				return RandomStringUtils.randomNumeric(Integer.valueOf(length));
			case SPECIAL:
				return RandomStringUtils.randomAscii(Integer.valueOf(length));
			default:
				throw new Error("Please call for right valid data type");
		}
	}

	/**
	 * <h1>This method is used to get instance from singleton class</h1>
	 * <b>Example:</b> FakerUtil.getInstance()
	 *
	 * @return singleton for this JVM class
	 */
	public static synchronized FakerUtil getInstance() {
		return Optional.ofNullable(INSTANCE).orElseGet(() -> {
			INSTANCE = new FakerUtil();
			return INSTANCE;
		});
	}

	/**
	 * <h1>This method is used to get valid credit card</h1>
	 * <b>Example:</b> validCreditCard(faker.finance().creditCard());
	 *
	 * @param ccNumber its number generated by faker util
	 * @return returns valid credit card number
	 */
	public String validCreditCard(String ccNumber) {
		if (ccNumber.startsWith("41") //VISA
				|| ccNumber.startsWith("54") //Mastercard
				|| ccNumber.startsWith("22") //Mastercard
				|| ccNumber.startsWith("37") //AMEX
				|| ccNumber.startsWith("601")) //Discover)
			return ccNumber.replace("-", "");
		else {
			return (validCreditCard(faker.finance().creditCard()));
		}
	}

	public String getRandomItem(List<String> givenList) {
		return givenList.get(new Random().nextInt(givenList.size()));
	}

	public String readStaticDataFromYaml(String key) {
		PropertiesUtil.loadYamlProperties(FileRepository.STATICDATA);
		return PropertiesUtil.getProperties(key);
	}

	public void productDataGenerator(String key) {
		ThreadLocalMap.getMap().put(key, getRandomItem(Arrays.asList(readStaticDataFromYaml(key).split(","))));
	}

	public int getIndexValueOfYmlData(String key, String value) {
		return Arrays.asList(readStaticDataFromYaml(key).split(",")).indexOf(value);
	}

	public List<String> returnYamlValueAsArray(String key) {
		return Arrays.asList(readStaticDataFromYaml(key).split(","));
	}

	public String getArrayCorrelationForStaticData(String firstKey, String secondKey) {
		return FakerUtil.getInstance().returnYamlValueAsArray(firstKey).get(FakerUtil.getInstance()
				.getIndexValueOfYmlData(secondKey, String.valueOf(ThreadLocalMap.getMap().get(secondKey))));
	}

//	public String getBroswerBasedEmail() {
//		JVMOptions jvmOptions = JVMOptions.getInstance();
//        String emailKey = CommonUtils.getDeviceType().toLowerCase()+
//				          "." + jvmOptions.getPlatform().replaceAll("[^a-zA-Z]", "").toLowerCase() +
//						  "." +
//				          jvmOptions.getBrowserName().toLowerCase();
//      	LOGGER.info("getBroswerBasedEmail: emailKey={}", emailKey);
//		return readStaticDataFromYaml(emailKey);
//	}

	public By getByObjectFromElement(String element) {
	    try {
            switch (element.replace(" ", "").toLowerCase()) {
                case "accountprofileheader":
                    return By.cssSelector(" div.fabric-account-headings-component > h1");
				case "firstprodictqty":
					return By.xpath("(//span[@class='product-selection-attribute-value'])[3]");
                default:
                    LOGGER.info("getByObjectFromElement: Not Valid Element, element name={}", element);
                    return null;
            }
        }catch(Exception e){
	        LOGGER.error("getByObjectFromElement: Error Occured while getting By Object, exception={}", e);
        }
        return null;
	}

}
