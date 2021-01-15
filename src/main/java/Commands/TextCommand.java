package Commands;

public abstract class TextCommand {
    protected String args;
    protected Object output;
    protected String PREFIX;
    public TextCommand(String args, String PREFIX) throws Exception {
        this.args = args;
        this.PREFIX = PREFIX;
        run();
    }
    protected void run() throws Exception {
    }
    public Object getOutput(){
        return output;
    }
}
