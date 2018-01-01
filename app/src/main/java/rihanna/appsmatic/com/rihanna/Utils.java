package rihanna.appsmatic.com.rihanna;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Eng Ali on 1/1/2018.
 */
public class Utils {
    private static Gson gson;

    public static Gson getGsonParser() {
        if(null == gson) {
            GsonBuilder builder = new GsonBuilder();
            gson = builder.create();
        }
        return gson;
    }
}
