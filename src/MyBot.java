    /**
     * Created by kann on 26-11-2014.
     */
    import org.jibble.pircbot.*;


    import java.io.FileInputStream;
    import java.io.IOException;
    import java.security.SecureRandom;
    import java.util.Date;
    import java.util.Properties;


    public class MyBot extends PircBot {
        Properties authConfig = new Properties();

        public MyBot() {
            this.setName("KannBot");
            this.setLogin("lopstar");

        }
        //Checking if the user is operator
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
            //Tells really bad danish jokes from phrases class.
            if(message.equalsIgnoreCase("!joke")){
                SecureRandom random = new SecureRandom();
                sendMessage(channel, phrases.jokePhrases[random.nextInt(phrases.jokePhrases.length)]);
            }
        }

        /*
        Added a half-way solution to my auth and mode problems. Now i need to make a login system
        So it's only users who are logged in, that can use these commands.
        -The auth is only used for testing, so stealing it will not give you much :)
         */
        public void onPrivateMessage(String sender, String login, String hostname, String message){
            if(message.equalsIgnoreCase("!auth" )){
                String Q =  "Q@CServe.quakenet.org";
                sendMessage(Q,"AUTH Kann b2NVRQiRty");
            }
            if(message.equalsIgnoreCase("!mode")){
                setMode(getName(), "+x");
            }
        }


        // Functions when people joins the channel.
        public void onJoin(String channel, String sender, String login, String hostname) {
            //welcomes people.
            sendMessage(channel, "Welcome to " +channel+", "+sender);
            //Gives voice to all
            voice(channel, hostname);

        }

    }


