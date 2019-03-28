package enums;


/**
 * <h1>This is the enum containing all the different Test types </h1>
 */

public enum TestTypes {
	FABRIC_VISUAL("@visual-component-ui"),
	FABRIC_FUNCTIONAL("@functional-component-ui"),
	INTEGRATED_FUNCTIONAL("@functional-integrated");

	public final String testType;


	/**
	 * <h1> This is a Constructor where the enums gets instantiated with values </h1>
	 *<p>
	 * <b>Example:</b>
	 * @param testType Compiler automatically passes the predefined values while creation
	 */
	TestTypes(String testType) {
		this.testType = testType;
	}


	/**
	 *<h1> This is the method to get enum values of respective enums</h1>
	 *<p>
	 * <b>Example:</b> TestTypes.FABRIC_VISUAL.getTestType()
	 * @return
	 */
	public String getTestType() {
		return testType;
	}
}
