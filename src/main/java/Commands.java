import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Commands {
    String PREFIX;
    public Commands(String PREFIX){
        this.PREFIX = PREFIX;
    }

    public void evaluateOnMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String prefixComparison = message.substring(0,PREFIX.length());
        if (prefixComparison.equals(PREFIX)){
            message = message.substring(PREFIX.length());
            switch (message){
                case "help":
                    event.getChannel().sendMessage("Help\n\nPrefix is \"" + PREFIX + "\"\n\nhelp : Returns this\nping : Returns Pong").queue();
                    break;
            }
        }
    }
    public void evaluateOnGuildMessageReceived(GuildMessageReceivedEvent event){
        String message = event.getMessage().getContentRaw();
        String prefixComparison = message.substring(0,PREFIX.length());
        if (prefixComparison.equals(PREFIX)){
            message = message.substring(PREFIX.length());
            switch (message){
                case "ping":
                    event.getChannel().sendMessage("Pong!").queue();
                    break;
            }
        }
    }
}
