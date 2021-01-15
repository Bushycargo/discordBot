package APIs;

import Utility.SettingsOptions;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class RedditAPI {
    UserAgent userAgent;
    Credentials credentials;
    NetworkAdapter networkAdapter;
    RedditClient redditClient;

    public RedditAPI() throws IOException, ParseException {
        userAgent = new UserAgent("bot", "com.bushycargo", "v0.0.1", "bushycargo");
        credentials = Credentials.script(SettingsOptions.GetSetting("RedditUser"), SettingsOptions.GetSetting("RedditPassword"),
                SettingsOptions.GetSetting("RedditOAuthToken"), SettingsOptions.GetSetting("RedditOAuthSecret"));

        networkAdapter = new OkHttpNetworkAdapter(userAgent);
        redditClient = OAuthHelper.automatic(networkAdapter,credentials);
    }
}
