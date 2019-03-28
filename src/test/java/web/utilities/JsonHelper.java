package web.utilities;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.jayway.jsonpath.JsonPath;

import java.util.List;


public class JsonHelper {

//    public String readJsonPath(String jsonString, String jsonPath){
//        JsonObject object = Json.parse(jsonString).asObject();
//        return object.get(jsonPath).asString();
//    }

    public String readJsonPath(String jsonString, String jsonPath) throws Exception {
        Object jsonPathResult= JsonPath.read(jsonString,jsonPath);
        if (null == jsonPathResult) {
            throw new Exception("Invalid JSON path provided!");
        }
        else   if (jsonPathResult instanceof List && ((List<?>)jsonPathResult).isEmpty()) {
            return "NULL";
        }
        else {
            return jsonPathResult.toString();
        }
    }
}
