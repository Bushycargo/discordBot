package Utility;

import net.dv8tion.jda.api.entities.Guild;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GuildDependentSettings {

    public static void SetDefaultSettings(Guild guild) throws IOException, ParseException {
        JSONObject dependentSettings;
        SetDependentSetting("PREFIX", guild, SettingsOptions.GetSetting("PREFIX"));
    }

    public static String GetDependentSetting(String KEY, Guild guild) throws IOException, ParseException {
        JSONObject dependentSettings;

        try {
            dependentSettings = (JSONObject) new JSONParser().parse(new FileReader("dependentSettings.json"));
        } catch (ParseException | IOException e) {
            dependentSettings = (JSONObject) new JSONParser().parse(new FileReader("build/libs/dependentSettings.json"));
        }
        JSONObject guildSettings = (JSONObject) dependentSettings.get(guild.getId());
        return guildSettings.get(KEY).toString();
    }

    public static void SetDependentSetting(String KEY, Guild guild, String setting) throws IOException, ParseException {
        JSONObject dependentSettings;
        if (KEY.equals("PREFIX")){
            setting = setting + " ";
        }
        try {
            try {
                dependentSettings = (JSONObject) new JSONParser().parse(new FileReader("dependentSettings.json"));
            } catch (ParseException | IOException e) {
                dependentSettings = (JSONObject) new JSONParser().parse(new FileReader("build/libs/dependentSettings.json"));
            }
            JSONObject guildSettings = (JSONObject) dependentSettings.get(guild.getId());

            dependentSettings.remove(guild.getId());
            guildSettings.remove(KEY);
            guildSettings.put(KEY, setting);
            dependentSettings.put(guild.getId(), guildSettings);

            FileWriter dependentSettingsWriter = new FileWriter("dependentSettings.json");
            dependentSettingsWriter.write(dependentSettings.toString());
            dependentSettingsWriter.flush();
            dependentSettingsWriter.close();

        } catch (IOException | ParseException e) {
            dependentSettings = new JSONObject();
            JSONObject guildSettings = new JSONObject();
            guildSettings.put(KEY, setting);
            dependentSettings.put(guild.getId(), guildSettings);
            FileWriter dependentSettingsWriter = new FileWriter("dependentSettings.json");
            dependentSettingsWriter.write(dependentSettings.toString());
            dependentSettingsWriter.flush();
            dependentSettingsWriter.close();
        }
    }
}