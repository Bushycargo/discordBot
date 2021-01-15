package Commands.APICommands.Twitter;
import APIs.TwitterAPI;
import Commands.TextCommand;
import twitter4j.Status;
import twitter4j.TwitterException;

public class LatestTweet extends TextCommand {
    public LatestTweet(String args, String PREFIX) throws Exception {
        super(args, PREFIX);
    }

    @Override
    protected void run() throws Exception {
        TwitterAPI twitterAPI = new TwitterAPI();
        Status tweet;
        if (args.equals("") || args.contains(" ")){
            output = "Invalid User";
        }
        else {
            try {
                tweet = twitterAPI.getTimeline(args).get(0);
                output = "https://twitter.com/" + tweet.getUser().getScreenName() + "/status/" + tweet.getId();
            } catch (TwitterException twitterException) {
                output = "Invalid User";
            }
        }
    }
}
