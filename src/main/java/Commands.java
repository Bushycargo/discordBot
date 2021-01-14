import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
                                "ping : Returns Pong\n" +
                                "random : Returns a random number\n" +
                                "fuck : Fuck that arg!").queue();
                        break;
                    }
                    else{
                        switch (args){
                            case "random":
                                event.getChannel().sendMessage("random [Integer lower], [Integer upper]\n" +
                                        "Will give a random number from the lower number to the upper number, including the lower but not the upper").queue();
                                break;
                            case "fuck":
                                event.getChannel().sendMessage("fuck [String argument]\n" +
                                        "The command will give the bot's opinion on something").queue();
                                break;
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
                case "random":
                    if (args.equals("")){
                        event.getChannel().sendMessage("Invalid arguments. Do \"" + PREFIX + "help random\" for a list of arguments").queue();
                    }
                    else {
                        Integer split = args.indexOf(',');
                        Integer firstValue = Integer.parseInt(args.substring(0, split));
                        Integer secondValue = Integer.parseInt(args.substring(split + 1).trim());
                        Integer random = new Random().nextInt(secondValue-firstValue) + firstValue;
                        event.getChannel().sendMessage(random.toString()).queue();
                    }
            }
        }
    }
    public void evaluateOnGuildMessageReceived(GuildMessageReceivedEvent event){
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
                    event.getChannel().sendMessage("Pong!").queue();
                    break;
                case "fuck":
                    switch (args){
                        case "":
                            event.getChannel().sendMessage("Invalid arguments. Do \"" + PREFIX + "help fuck\" for a list of arguments").queue();
                            break;
                        case "me":
                            event.getChannel().sendMessage("Fuck you").queue();
                            break;
                        default:
                            event.getChannel().sendMessage("Yeah, fuck " + args + "! All my homies hate " + args + ".").queue();
                            break;
                    }
                    break;
            }
        }
    }
    public void evaluateOnPrivateMessageReceived(PrivateMessageReceivedEvent event){
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
                    event.getChannel().sendMessage("Pong!").queue();
                    break;
            }
        }
    }
}
