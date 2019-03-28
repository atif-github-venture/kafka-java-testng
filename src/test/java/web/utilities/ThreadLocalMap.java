package web.utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>This is a class containing all the methods related to operations on Thread Local</h1>
 */

public class ThreadLocalMap {


	private static ThreadLocal<HashMap<String, Object>> threadLocalMap = ThreadLocal.withInitial(HashMap::new);

	private ThreadLocalMap() {
	}

	/**
	 * <h1>Removes current value from the threadLocalMap</h1>
	 * <p>
	 */
	public static void cleanup() {
		threadLocalMap.remove();
	}

	/**
	 * <h1>Returns the value to which the specified key is mapped </h1>
	 * <p>
	 * @param key the key whose associated value is to be returned
	 * @param type the type of Class
	 * @param <T> Class Type to be returned
	 * @return  Value of type <T>
	 */
	public static <T> T getItem(String key, Class<T> type) {
		return type.cast(getMap().get(key));
	}

	/**
	 * <h1>To get the current value in the threadLocalMap variable</h1>
	 * <p>
	 * @return Current Map Object from threadLocalMap variable
	 */
	public static Map<String, Object> getMap() {
		return threadLocalMap.get();
	}
}
