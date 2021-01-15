import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import Commands.*;

public class Commands {
    private final String PREFIX;
    private final Integer VERBOSITY;
    public Commands(String PREFIX, Integer VERBOSITY){
        this.PREFIX = PREFIX;
        this.VERBOSITY = VERBOSITY;
    }
    public void evaluateOnMessageReceived(MessageReceivedEvent event) throws Exception {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String message = event.getMessage().getContentRaw();
        String command;
        String args;
        String prefixComparison = message.substring(0,PREFIX.length());
        if (prefixComparison.equals(PREFIX)){
            message = message.substring(PREFIX.length());
            if (VERBOSITY >= 2){
                System.out.println(timeStamp + ": " + event.getMessage().getContentRaw());
            }
            try {
                command = message.substring(0, message.indexOf(' '));
                args = message.substring(message.indexOf(' ') + 1);
            }
            catch (Exception e){
                command = message;
                args = "";
            }
            switch (command){
                case "help":
                    event.getChannel().sendMessage(new Help(args, PREFIX).getOutput().toString()).queue();
                    break;
                case "mama":
                    event.getChannel().sendMessage(new MamaJoke(args,PREFIX).getOutput().toString()).queue();
                    break;
                case "random":
                    event.getChannel().sendMessage(new RandomNumber(args, PREFIX).getOutput().toString()).queue();
                    break;
            }
        }
    }
    public void evaluateOnGuildMessageReceived(GuildMessageReceivedEvent event) throws Exception {
        String message = event.getMessage().getContentRaw();
        String command;
        String args;
        String prefixComparison = message.substring(0,PREFIX.length());
        if (prefixComparison.equals(PREFIX)){
            message = message.substring(PREFIX.length());
            try {
                command = message.substring(0, message.indexOf(' '));
                args = message.substring(message.indexOf(' ') + 1);
            }
            catch (Exception e){
                command = message;
                args = "";
            }
            switch (command){
                case "join":
                    Voice voice = new Voice();
                    voice.Connect(event, "General");
                    break;
                case "ping":
                    event.getChannel().sendMessage(new Ping(args, PREFIX).getOutput().toString()).queue();
                    break;
                case "fuck":
                    event.getChannel().sendMessage(new FuckSomething(args, PREFIX).getOutput().toString()).queue();
                    break;
            }
        }
    }
    public void evaluateOnPrivateMessageReceived(PrivateMessageReceivedEvent event) throws Exception {
        String message = event.getMessage().getContentRaw();
        String command;
        String args;
        String prefixComparison = message.substring(0,PREFIX.length());
        if (prefixComparison.equals(PREFIX)){
            message = message.substring(PREFIX.length());
            try {
                command = message.substring(0, message.indexOf(' '));
                args = message.substring(message.indexOf(' ') + 1);
            }
            catch (Exception e){
                command = message;
                args = "";
            }
            switch (command){
                case "ping":
                    event.getChannel().sendMessage(new Ping(args, PREFIX).getOutput().toString()).queue();
                    break;
            }
        }
    }
}
