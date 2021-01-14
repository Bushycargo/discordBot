import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.IOException;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws Exception {
        JSONObject settings;
        try {
            settings = (JSONObject) new JSONParser().parse(new FileReader("settings.json"));
        }
        catch (IOException e){
            settings = (JSONObject) new JSONParser().parse(new FileReader("build/libs/settings.json"));
        }
        String BOT_TOKEN = settings.get("BOT_TOKEN").toString();
        String PREFIX = settings.get("PREFIX").toString();
        Integer VERBOSITY = 2;
        JDA api = JDABuilder.createDefault(BOT_TOKEN).build();
        api.addEventListener(new Listener(PREFIX, VERBOSITY));
    }
}
