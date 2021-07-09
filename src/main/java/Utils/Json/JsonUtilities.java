package Utils.Json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.util.Arrays;
import java.util.regex.Pattern;

public class JsonUtilities {
    /**
     * @param object
     * @param path json的路径，以.来分割，比如playground.name
     * @return
     */
    public static String fetch(JsonObject object, String path) throws Exception {
        if (!object.isJsonObject()){
            return object.getAsString();
        }

        var split = Arrays.stream(path.split(Pattern.quote("."))).toList();

        for (var p : split) {
            if (object != null) {
                var ele = object.get(p);

                if (ele == null){
                    throw new Exception("路径 " + p + " 不存在：" + path);
                }

                if (!ele.isJsonObject())
                    return ele.getAsString();
                else
                    object = ele.getAsJsonObject();
            }
            else {
                return null;
            }
        }

        return object.toString();
    }
}
