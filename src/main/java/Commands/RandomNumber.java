package Commands;

import java.util.Random;

public class RandomNumber extends TextCommand {
    public RandomNumber(String args, String PREFIX) throws Exception {
        super(args, PREFIX);
    }

    @Override
    protected void run() throws Exception {
        if (args.equals("")){
            output = "Invalid arguments. Do \"" + PREFIX + "help random\" for a list of arguments";
        }
        else {
            int split = args.indexOf(',');
            int firstValue = Integer.parseInt(args.substring(0, split));
            int secondValue = Integer.parseInt(args.substring(split + 1).trim());
            int random = new Random().nextInt(secondValue-firstValue) + firstValue;
            output = Integer.toString(random);
        }
    }
}
