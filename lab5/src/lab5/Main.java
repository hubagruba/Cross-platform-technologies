package lab5;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        SmartHome smartHome = new SmartHome();
        Scanner scanner = new Scanner(System.in);
        
        	System.out.println(
                    "add <type of component> - add \n" +
                    "remove + name - remove\n" +
                    "turnon  + name - turn on\n" +
                    "turnoff  + name - turn off\n" +
                    "timeron  + name + time(seconds) - set timer to turn on \n" +
                    "timeroff + name + time(seconds) - set timer to turn off\n" +
                    "temp+ + name + value - increase temperature of conditioner\n" +
                    "temp- + name + value - decrease temperature of conditioner\n" +
                    "channels + name - show channel list of specified TV" +
                    "setchannel+ name + channel number - set current channel of  TV \n" +
                    "info - print info about all components\n"+
                    "to do your choice: " );
        	for (;;) {
            String cmd = scanner.nextLine();
            System.out.println("");
            smartHome.performCommand(cmd);
            System.out.println("");
        }
    }
}
