package Commands.APICommands.Tenor;

import APIs.TenorAPI;
import Commands.TextCommand;

public class TenorGetGif extends TextCommand {
    public TenorGetGif(String args, String PREFIX) throws Exception {
        super(args, PREFIX);
    }

    @Override
    protected void run() throws Exception {
        output = TenorAPI.getGif(args);
    }
}
