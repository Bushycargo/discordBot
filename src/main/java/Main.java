import Utility.GuildDependentSettings;
import Utility.SettingsOptions;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws Exception {
        String BOT_TOKEN = SettingsOptions.GetSetting("BOT_TOKEN");
        String PREFIX = SettingsOptions.GetSetting("PREFIX");
        Integer VERBOSITY = 2;
        JDA api = JDABuilder.createDefault(BOT_TOKEN).build();
        api.addEventListener(new Listener(PREFIX, VERBOSITY));
    }
}
