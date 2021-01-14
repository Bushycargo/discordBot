import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listener extends ListenerAdapter {
    String PREFIX;
    Integer VERBOSITY;
    Commands commands;
    public Listener(String PREFIX, Integer VERBOSITY){
        this.PREFIX = PREFIX;
        this.VERBOSITY = VERBOSITY;
        commands = new Commands(this.PREFIX, this.VERBOSITY);
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
            commands.evaluateOnMessageReceived(event);
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
        commands.evaluateOnGuildMessageReceived(event);
    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        commands.evaluateOnPrivateMessageReceived(event);
    }
}