package APIs;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class RedditAPI {
    UserAgent userAgent;
    Credentials credentials;
    NetworkAdapter networkAdapter;
    RedditClient redditClient;

    public RedditAPI() throws IOException, ParseException {
        userAgent = new UserAgent("bot", "com.bushycargo", "v0.0.1", "bushycargo");

        JSONObject settings;
        try {
            settings = (JSONObject) new JSONParser().parse(new FileReader("settings.json"));
        }
        catch (IOException e){
            settings = (JSONObject) new JSONParser().parse(new FileReader("build/libs/settings.json"));
        }
        credentials = Credentials.script(settings.get("RedditUser").toString(), settings.get("RedditPassword").toString(),
                settings.get("RedditOAuthToken").toString(), settings.get("RedditOAuthSecret").toString());

        networkAdapter = new OkHttpNetworkAdapter(userAgent);
        redditClient = OAuthHelper.automatic(networkAdapter,credentials);
    }
}
