package enums;

/**
 * <h1>This is the enum containing all the Tags </h1>
 */

public enum Tags {

	REGRESSION("@regression"),
	COMPONENT("@component"),
	TEMPLATE("@template"),
	DP("@DP"),
	FATE("@FATE"),
	OUTLINE("@outline");

	private String tagName;

	/**
	 * <h1> This is a Constructor where the enums gets instantiated with values </h1>
	 *<p>
	 * <b>Example:</b>
	 * @param tagName Compiler automatically passes the predefined values while creation
	 */
	Tags(String tagName) {
		this.tagName = tagName;
	}

	/**
	 *<h1> This is the method to get enum values of respective enums</h1>
	 *<p>
	 * <b>Example:</b> Tags.DP.getTagName()
	 * @return
	 */
	public String getTagName() {
		return tagName;
	}
}
