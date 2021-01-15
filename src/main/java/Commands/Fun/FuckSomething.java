package Commands.Fun;

import Commands.TextCommand;

public class FuckSomething extends TextCommand {
    public FuckSomething(String args, String PREFIX) throws Exception {
        super(args, PREFIX);
    }

    @Override
    protected void run() throws Exception {
        switch (args){
            case "":
                output = "Invalid arguments. Do \"" + PREFIX + "help fuck\" for a list of arguments";
                break;
            case "me":
                output = "FuckSomething you";
                break;
            default:
                output = "Yeah, fuck " + args + "! All my homies hate " + args + ".";
                break;
        }
    }
}
