package Commands.Fun.Jokes;

import Commands.TextCommand;

public class MamaJoke extends TextCommand {
    public MamaJoke(String args, String PREFIX) throws Exception {
        super(args, PREFIX);
    }

    @Override
    protected void run() throws Exception {
        if (args.equals("")){
            output = Jokes.RandomJoke();
        }
        else{
            try {
                output = Jokes.RandomJoke(args);
            } catch (Exception e) {
                output = "Invalid arguments. Do \"" + PREFIX + "help mama\" for a list of arguments";
            }
        }
    }
}
