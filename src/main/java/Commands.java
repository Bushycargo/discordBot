import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Commands {
    String PREFIX;
    Integer VERBOSITY;
    public Commands(String PREFIX, Integer VERBOSITY){
        this.PREFIX = PREFIX;
        this.VERBOSITY = VERBOSITY;
    }

    public void evaluateOnMessageReceived(MessageReceivedEvent event) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String message = event.getMessage().getContentRaw();
        String prefixComparison = message.substring(0,PREFIX.length());
        if (prefixComparison.equals(PREFIX)){
            message = message.substring(PREFIX.length());
            if (VERBOSITY >= 2){
                System.out.println(timeStamp + ": " + event.getMessage().getContentRaw());
            }
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
    public void evaluateOnPrivateMessageReceived(PrivateMessageReceivedEvent event){
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
