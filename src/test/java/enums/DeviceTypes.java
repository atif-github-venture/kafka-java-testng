package enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * <h1>This is the enum containing all the Test Devices </h1>
 */

public enum DeviceTypes {

	DESKTOP("desktop"),
	TABLET("tablet"),
	MOBILE("mobile");

	private static final Logger LOGGER = LogManager.getLogger(DeviceTypes.class);

	private String deviceType;

	/**
	 * <h1> This is a Constructor where the enums gets instantiated with values </h1>
	 *<p>
	 * <b>Example:</b>
	 * @param deviceType Compiler automatically passes the predefined values while creation
	 */
	DeviceTypes(String deviceType) {
		this.deviceType = deviceType;
	}


	/**
	 *<h1> This is the method to get enum values of respective enums</h1>
	 *<p>
	 * <b>Example:</b> DeviceTypes.DESKTOP.getDeviceType()
	 * @return
	 */
	public String getDeviceType() {
		return deviceType;
	}


	/**
	 *<h1> This is the method to find and return the Device Types from a list of enum values </h1>
	 *<p>
	 * <b>Example:</b>BrowserTypes.fromString ("desktop")
	 * @param value This is the name to be passed as string ("desktop")
	 * @return
	 */
	public static DeviceTypes fromString(String value) {
		for (DeviceTypes deviceType : values()) {
			if (value != null && value.toLowerCase().equals(deviceType.getDeviceType())) {
				return deviceType;
			}
		}
		LOGGER.info("Invalid Device Type passed as 'device' property. "
				+ "One of: " + Arrays.toString(values()) + " is expected.");
		return DESKTOP;
	}
}
