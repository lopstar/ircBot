/**
 * Created by kann on 26-11-2014.
 */
import org.jibble.pircbot.*;

public class MyBotMain {

    public static void main(String[] args) throws Exception {

        // Now start our bot up.
        MyBot bot = new MyBot();

        // Enable debugging output.
        bot.setVerbose(true);

        // Connect to the IRC server.
        bot.connect("irc.quakenet.org");

        // Join the #pircbot channel.
        bot.joinChannel("#lopstar");
        bot.joinChannel("#needclan.dk");

    }

}