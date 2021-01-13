import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Listener extends ListenerAdapter {
    String PREFIX;
    Commands commands;
    public Listener(String PREFIX){
        this.PREFIX = PREFIX;
        commands = new Commands(this.PREFIX);
    }
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.println("JDA loaded and ready!");
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        commands.evaluateOnMessageReceived(event);
    }

    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        commands.evaluateOnGuildMessageReceived(event);
    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equals(".ping")) {
            event.getChannel().sendMessage("Private Pong!").queue();
        }
    }
}