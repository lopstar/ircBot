    /**
     * Created by kann on 26-11-2014.
     */
    import org.jibble.pircbot.*;

            import java.util.Date;

    public class MyBot extends PircBot {

        public MyBot() {
            this.setName("KannBot");
            this.setLogin("lopstar");

        }

        // added a time function to check the time.
        public void onMessage(String channel, String sender, String login, String hostname, String message) {
            if(message.equalsIgnoreCase("!time")) {
                String time = new Date().toString();
                sendMessage(channel, sender + ": The time is now " + time);

            }
        }
        // welcomes who ever joins the channel.
        public void onJoin(String channel, String sender, String login, String hostname) {
            sendMessage(channel, "Welcome to " +channel+", "+sender);

        }
        /* Trying to get the bot to authenticate and after hide it's hostmask, but having some problems.

        public void onConnect(){
            sendRawLine("/msg Q auth lopstar m87V!yJxSY");
            sendRawLine("/mode KannBot +x");
        }
        */
    }

