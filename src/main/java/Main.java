import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        //SET UP ---------
        String BOT_TOKEN = "NDEzNDAwOTcyNTgxMjczNjEx.WoR_qA.TLgsaz3WnzB32Fp2oNBDW1ncxLY";
        String PREFIX = "yo ";

        JDA api = JDABuilder.createDefault(BOT_TOKEN).build();
        api.addEventListener(new Listener(PREFIX));
    }
}
