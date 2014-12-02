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

        public boolean isOp(String username){
            if(username.startsWith("@")) {
                return true;
            } else {
                return false;
            }
        }

        //Service commands
        public void onMessage(String channel, String sender, String login, String hostname, String message) {
            //Checking time
            if (message.equalsIgnoreCase("!time")) {
                String time = new Date().toString();
                sendMessage(channel, sender + ": The time is now " + time);
            }
            //Checking uptime
            if (message.equalsIgnoreCase("!uptime")) {
                sendMessage(channel, "The bot has been online for: " + RPL_STATSUPTIME / 60 + " seconds");
            }
            //Cheking amount of time
            if (message.equalsIgnoreCase("!users")) {
                int totalUsersOnline = getUsers(channel).length;
                sendMessage(channel, "Users in channel is: " + totalUsersOnline);
            }
        }
        
        // Functions when people joins the channel.
        public void onJoin(String channel, String sender, String login, String hostname) {
            //welcomes people.
            sendMessage(channel, "Welcome to " +channel+", "+sender);
            //Gives voice to all
            voice(channel, hostname);
            //Working on this by it only should give op to the admins with the correct auth on quakenet.
            op(channel, "lopstar1");

        }
        /* Trying to get the bot to authenticate and after hide it's hostmask, but having some problems.

        public void onConnect(){
            sendRawLine("/msg Q auth lopstar m87V!yJxSY");
            sendRawLine("/mode KannBot +x");
        }
        */
    }

