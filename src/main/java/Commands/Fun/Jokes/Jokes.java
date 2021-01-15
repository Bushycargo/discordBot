package Commands.Fun.Jokes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public abstract class Jokes {
    public static String RandomJoke(String type) throws Exception {
        Object object;
        try {
            object = new JSONParser().parse(new FileReader("src/main/resources/jokes.json"));
        } catch (IOException e) {
            object = new JSONParser().parse(new FileReader("jokes.json"));
        }
        JSONObject jsonObject = (JSONObject) object;
        JSONArray jokes = (JSONArray) jsonObject.get(type);
        return jokes.get(new Random().nextInt(jokes.size()-1)).toString();
    }
    public static String RandomJoke() throws Exception{
        String[] jokeTypes = {"fat", "stupid", "ugly", "nasty", "hairy", "bald", "old", "poor", "short", "skinny", "tall", "like"};
        return RandomJoke(jokeTypes[new Random().nextInt(jokeTypes.length-1)]);
    }
}
