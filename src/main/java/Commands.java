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
                    if (args.equals("")){
                        event.getChannel().sendMessage("Help\n\n" +
                                "Prefix is \"" + PREFIX + "\"\n\n" +
                                "help : Returns this, if you do help then a command it will tell you available arguments for a command\n" +
                                "mama : Returns a yo mama joke\n" +
                                "ping : Returns Pong").queue();
                        break;
                    }
                    else{
                        switch (args){
                            case "help":
                                event.getChannel().sendMessage("help [String command]\n" +
                                        "The help command will tell you the arguments for a specific command if entered else it will display the help message").queue();
                                break;
                            case "mama":
                                event.getChannel().sendMessage("mama [String type]\n" +
                                        "The mama command will output a yo mama joke at random if no type specified\n" +
                                        "Possible Types:\n" +
                                        "Fat\n" +
                                        "Stupid\n" +
                                        "Ugly\n" +
                                        "Nasty\n" +
                                        "Hairy\n" +
                                        "Bald\n" +
                                        "Old\n" +
                                        "Poor\n" +
                                        "Short\n" +
                                        "Skinny\n" +
                                        "Tall\n" +
                                        "Like").queue();
                                break;
                            case "ping":
                                event.getChannel().sendMessage("Returns pong").queue();
                                break;
                        }
                    }
                    break;
                case "mama":
                    if (args.equals("")){
                        event.getChannel().sendMessage(Jokes.RandomJoke()).queue();
                    }
                    else {
                        try {
                            event.getChannel().sendMessage(Jokes.RandomJoke(args)).queue();
                        }
                        catch (Exception e){
                            event.getChannel().sendMessage("Invalid arguments. Do \"" + PREFIX + "help mama\" for a list of arguments").queue();
                        }
                    }
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
