import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Listener extends ListenerAdapter {
    String PREFIX = ".";

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println("JDA loaded and ready!");
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        if (event.getMessage().getContentRaw().equals(PREFIX + "help")){
            event.getChannel().sendMessage("Help\n\nPrefix is: \"" + PREFIX +"\"\n\nCommands:\nPing : Returns Pong").queue();
        }
    }

    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equals(PREFIX + "ping")) {
            event.getChannel().sendMessage("Guild Pong!").queue();
        }
    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equals(PREFIX + "ping")) {
            event.getChannel().sendMessage("Private Pong!").queue();
        }
    }
}