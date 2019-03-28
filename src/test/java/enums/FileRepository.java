package enums;


/**
 * <h1>This is the enum containing all the .yml files Repository </h1>
 */
public enum FileRepository {

	ENVPROPS("configuration/environment.yml"),
	GETPROPS("configuration/webservices/uri/get.yml"),
	PUTPROPS("configuration/webservices/uri/put.yml"),
	POSTPROPS("configuration/webservices/uri/post.yml"),
	DELPROPS("configuration/webservices/uri/delete.yml"),
	STATICDATA("data/static/staticdata.yml"),
	URL("data/static/url.yml");

	private String value;

	/**
	 * <h1> This is a Constructor where the enums gets instantiated with values </h1>
	 *<p>
	 * <b>Example:</b>
	 * @param value Compiler automatically passes the predefined values while creation
	 */
	FileRepository(String value) {
		this.value = value;
	}


	/**
	 *<h1> This is the method to get enum values of respective enums</h1>
	 *<p>
	 * <b>Example:</b> FileRepository.ENVPROPS.getValue()
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <h1> This is the method to return string values of the respective enums</h1>
	 *
	 * <b>Example:</b> FileRepository.ENVPROPS.toString()
	 * @return
	 */
	@Override
	public String toString() {
		return this.getValue();
	}
}
