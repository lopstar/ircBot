    /**
     * Created by kann on 26-11-2014.
     */
    import org.jibble.pircbot.*;

    import java.security.SecureRandom;
    import java.util.Date;



    public class MyBot extends PircBot {
     private String AUTH_name = "lopstar";
        private String password ="password";
        private String controller ="";

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
            A private message system, where you only can use the commands if you are logged in with an auth name and password.
         */
        public void onPrivateMessage(String sender, String login, String hostname, String message) {
            //Quakenet IdentServer
            String Q = "Q@CServe.quakenet.org";
            //Login function
            if( message.startsWith("!login")) {
                if (message.equals("!login " + AUTH_name + " " + password)) {
                    controller = sender;
                    sendMessage(sender, "You are now logged in");
                } else if (controller.isEmpty()) {
                    sendMessage(sender, "Wrong username or password, try again");
                }
                //Authentication with quakenet for the bot.
            } else if( message.startsWith("!auth")) {
                if (message.equals("!auth") && controller.equals(sender)) {
                    sendMessage(Q, "AUTH Kann b2NVRQiRty");
                } else if(controller.isEmpty()) {
                    sendMessage(sender, "You must be logged in to use this command");
                }
                //Function for hide the hostmask for the bot.
            } else if( message.startsWith("!mode")) {
                if (message.equalsIgnoreCase("!mode") && controller.equals(sender)) {
                    setMode(getName(), "+x");
                } else if (controller.isEmpty()) {
                    sendMessage(sender, "You must be logged in to use this command");

                }
            }
        }


        //Function that reacts when people join the channel
        public void onJoin(String channel, String sender, String login, String hostname) {
            //welcomes people.
            sendMessage(channel, "Welcome to " +channel+", "+sender);
            //Gives voice to all
            voice(channel, hostname);

        }

    }


