package Utility;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class SettingsOptions {
    public static String GetSetting(String KEY) throws IOException, ParseException {
        JSONObject settings;
        try {
            settings = (JSONObject) new JSONParser().parse(new FileReader("settings.json"));
        }
        catch (IOException | ParseException e){
            settings = (JSONObject) new JSONParser().parse(new FileReader("build/libs/settings.json"));
        }
        return settings.get(KEY).toString();
    }
}
