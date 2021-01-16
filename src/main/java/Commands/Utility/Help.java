package Commands.Utility;

import Commands.TextCommand;

public class Help extends TextCommand {
    public Help(String args, String PREFIX) throws Exception {
        super(args, PREFIX);
    }

    @Override
    protected void run() {
        if (args.equals("")){
            output = "Help\n\n" +
                    "Prefix is \"`" + PREFIX + "`\"\n\n" +
                    "`help` : Returns this, if you do help then a command it will tell you available arguments for a command\n" +
                    "`mama` : Returns a yo mama joke\n" +
                    "`ping` : Returns Pong\n" +
                    "`random` : Returns a random number\n" +
                    "`new-tweet` : Returns the latest tweet from a twitter user\n" +
                    "`gif` : Returns a gif\n" +
                    "`fuck` : Fuck that arg!";
        }
        else{
            switch (args){
                case "gif":
                    output = "`gif [Optional Search Parameter]`\n" +
                            "Will give you a gif depending on search parameter, if blank it'll be randomish (Kinda buggy right now)";
                case "new-tweet":
                    output="`new-tweet [String user]`\n" +
                            "Will give the latest tweet from a twitter user, if it can not: returns \"Invalid User\"";
                    break;
                case "random":
                    output = "`random [Integer lower], [Integer upper]`\n" +
                            "Will give a random number from the lower number to the upper number, including the lower but not the upper";
                    break;
                case "fuck":
                    output = "`fuck [String argument]`\n" +
                            "The command will give the bot's opinion on something";
                    break;
                case "help":
                    output = "`help [String command]`\n" +
                            "The help command will tell you the arguments for a specific command if entered else it will display the help message";
                    break;
                case "mama":
                    output = "`mama [String type]`\n" +
                            "The mama command will output a yo mama joke at random if no type specified\n\n" +
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
                            "Like";
                    break;
                case "ping":
                    output = "Returns pong";
                    break;
                default:
                    output = "Not valid help page.\nDo `yo help` for a list of commands";
            }
        }
    }
}
