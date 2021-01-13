import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Commands {
    String PREFIX;
    public Commands(String PREFIX){
        this.PREFIX = PREFIX;
    }

    public void evaluateOnMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (String.valueOf(message.charAt(0)).equals(PREFIX)){
            message = message.substring(1);
            switch (message){
                case "help":
                    event.getChannel().sendMessage("Help\n\nPrefix is \"" + PREFIX + "\"\n\nhelp : Returns this\nping : Returns Pong").queue();
                    break;
                default:
                    event.getChannel().sendMessage("Unknown Command. Do " + PREFIX + "help for a list of commands").queue();
                    break;
            }
        }
    }
}
