import Utility.GuildDependentSettings;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listener extends ListenerAdapter {
    Integer VERBOSITY;
    String privatePREFIX;
    public Listener(String PREFIX, Integer VERBOSITY){
        this.VERBOSITY = VERBOSITY;
        privatePREFIX = PREFIX;
    }
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        if (VERBOSITY >= 1){
            System.out.println("JDA loaded and ready!");
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        try {
            new Commands(GuildDependentSettings.GetDependentSetting("PREFIX", event.getGuild()), VERBOSITY).evaluateOnMessageReceived(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (VERBOSITY >= 3){
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            System.out.println(timeStamp + ": " + event.getMessage().getContentRaw());
        }
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        try {
            new Commands(GuildDependentSettings.GetDependentSetting("PREFIX", event.getGuild()), VERBOSITY).evaluateOnGuildMessageReceived(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        try {
            new Commands(privatePREFIX, VERBOSITY).evaluateOnPrivateMessageReceived(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        try {
            GuildDependentSettings.SetDefaultSettings(event.getGuild());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}