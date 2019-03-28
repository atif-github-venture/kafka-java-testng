package enums;

import java.util.Arrays;

/**
 * <h1>This is the enum containing all the Browser Types </h1>
 */

public enum BrowserTypes {

	FIREFOX("firefox"),
	CHROME("chrome"),
	SAFARI("safari"),
	EDGE("microsoftedge");

	private String name;

	/**
	 * <h1> This is a Constructor where the enums gets instantiated with values </h1>
	 *<p>
	 * <b>Example:</b>
	 * @param name Compiler automatically passes the predefined values while creation
	 */
	BrowserTypes(String name) {
		this.name = name;
	}

	/**
	 *<h1> This is the method to get enum Names </h1>
	 *<p>
	 * <b>Example:</b> BrowserTypes.FIREFOX.getName()
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 *<h1> This is the method to find and return the browser Name from a list of enum values </h1>
	 *<p>
	 * <b>Example:</b>BrowserTypes.fromString ("chrome")
	 * @param value This is the name to be passed as string ("chrome","16110")
	 * @return
	 */
	public static BrowserTypes fromString(String value) {
		for (BrowserTypes browser : values()) {
			if (value != null && value.toLowerCase().equals(browser.getName())) {
				return browser;
			}
		}
		System.out.println("Invalid driver name passed as 'browser' property. "
				+ "One of: " + Arrays.toString(values()) + " is expected.");
		return CHROME;
	}

}
