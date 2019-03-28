package enums;

/**
 * <h1>This is the enum containing all the Work Stream Types </h1>
 */

public enum StreamType {
	COllECTION_SEARCH("@collection-search"),
	PRODUCT("@product-page"),
	ACCOUNTS("account-customer-service"),
	HOME_AND_LANDING("@home-landing"),
	CHECKOUT("@checkout");

	public final String streamType;

	/**
	 * <h1> This is a Constructor where the enums gets instantiated with values </h1>
	 *<p>
	 * <b>Example:</b>
	 * @param streamType Compiler automatically passes the predefined values while creation
	 */
	StreamType(String streamType) {
		this.streamType = streamType;
	}

	/**
	 *<h1> This is the method to get enum values of respective enums</h1>
	 *<p>
	 * <b>Example:</b> StreamType.COllECTION_SEARCH.getStreamType()
	 * @return
	 */
	public String getStreamType() {
		return streamType;
	}
}
