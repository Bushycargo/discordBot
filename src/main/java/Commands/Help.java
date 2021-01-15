package Commands;

public class Help extends TextCommand {
    public Help(String args, String PREFIX) throws Exception {
        super(args, PREFIX);
    }

    @Override
    protected void run() {
        if (args.equals("")){
            output = "Help\n\n" +
                    "Prefix is \"" + PREFIX + "\"\n\n" +
                    "help : Returns this, if you do help then a command it will tell you available arguments for a command\n" +
                    "mama : Returns a yo mama joke\n" +
                    "ping : Returns Pong\n" +
                    "random : Returns a random number\n" +
                    "fuck : FuckSomething that arg!";
        }
        else{
            switch (args){
                case "random":
                    output = "random [Integer lower], [Integer upper]\n" +
                            "Will give a random number from the lower number to the upper number, including the lower but not the upper";
                    break;
                case "fuck":
                    output = "fuck [String argument]\n" +
                            "The command will give the bot's opinion on something";
                    break;
                case "help":
                    output = "help [String command]\n" +
                            "The help command will tell you the arguments for a specific command if entered else it will display the help message";
                    break;
                case "mama":
                    output = "mama [String type]\n" +
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
                            "Like";
                    break;
                case "ping":
                    output = "Returns pong";
                    break;
            }
        }
    }
}
