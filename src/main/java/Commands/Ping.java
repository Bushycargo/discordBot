package Commands;

public class Ping extends TextCommand{
    public Ping(String args, String PREFIX) throws Exception {
        super(args, PREFIX);
    }

    @Override
    protected void run() throws Exception {
        output = "Pong!";
    }
}
