package APIs;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TwitterAPI {
    private Twitter twitter;

    public TwitterAPI() throws IOException, ParseException, TwitterException {
        JSONObject settings;
        try {
            settings = (JSONObject) new JSONParser().parse(new FileReader("settings.json"));
        }
        catch (IOException | ParseException e){
            settings = (JSONObject) new JSONParser().parse(new FileReader("build/libs/settings.json"));
        }

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthAccessToken(settings.get("TwitterOAuthAccessToken").toString())
                .setOAuthAccessTokenSecret(settings.get("TwitterOAuthAccessTokenSecret").toString())
                .setOAuthConsumerKey(settings.get("TwitterOAuthConsumerKey").toString())
                .setOAuthConsumerSecret(settings.get("TwitterOAuthConsumerSecret").toString());

        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        twitter = twitterFactory.getInstance();
    }
    public List<Status> getTimeline(String user) throws TwitterException {
        List<Status> statusList;
        statusList = twitter.getUserTimeline(user);
        return statusList;
    }
}
