    /**
     * Created by kann on 26-11-2014.
     */
    import org.jibble.pircbot.*;

            import java.util.Date;

    public class MyBot extends PircBot {

        public MyBot() {
            this.setName("KannBot");
        }


        public void onMessage(String channel, String sender, String login, String hostname, String message) {
            if(message.equalsIgnoreCase("!time")) {
                String time = new Date().toString();
                sendMessage(channel, sender + ": The time is now " + time);

            }
        }

        public void onJoin(String channel, String sender, String login, String hostname) {
            sendMessage(channel, "Welcome to " +channel+", "+sender); // welcomes who ever joins the channel.

        }

    }