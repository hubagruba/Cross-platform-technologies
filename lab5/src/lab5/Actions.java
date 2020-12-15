package lab5;

import java.util.ArrayList;


public class Actions {

    public boolean isCommandValid(String cmd) {
        String[] cmdParts = cmd.split(" ");
        String action = cmdParts[0];

        int len = cmdParts.length;
        if (len > 3){
            return false;
        }
        switch(action){
            case "add":
            case "remove":
            case "turnon":
            case "turnoff":
            case "channels":
                if (len != 2) {
                    return false;
                }
                return true;
            case "info":
            case "help":
                return true;
            case "timeron":
            case "timeroff":
            case "setchannel":
                if (len != 3){
                    return false;
                }
                try {
                    int value = Integer.parseInt(cmdParts[2]);
                    if (value < 0){
                        return false;
                    }
                }
                catch (NumberFormatException e){
                    return false;
                }
                return true;
            case "temp+":
            case "temp-":
                if (len != 3) {
                    return false;
                }
                try {
                    float value = Float.valueOf(cmdParts[2]);
                }
                catch (NumberFormatException e){
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    public void printStrArr(String[] arr, String desc) {
        System.out.println(desc);
        for(String s : arr){
            System.out.println(s);
        }
    }

    public void printInfoAboutSuccess(boolean isSucceed){
        if (isSucceed){
            System.out.println("Command succeeded.");
        }
        else {
            System.out.println("Command failed.");
        }
    }

    public void printDetailedInformation(ArrayList<Device> device){
        for (Device cmp : device){
            String cmpName = cmp.getName();
            System.out.println(cmpName + ":");

            if (cmp.isTurnedOn()){
                System.out.println("Is turned on.");

                String cmpType = cmp.getClass().getSimpleName();
                switch(cmpType){
                    case "TV":
                        System.out.println("Curr channel: " + ((TV)cmp).getCurrChannel());
                        break;
                    case "Conditioner":
                        System.out.println("Temperature: " + ((Conditioner)cmp).getTemp());
                        break;
                }
            }
            else{
                System.out.println("Is turned off.");
            }

            if (cmp.isTimerOn()){
                if (cmp.isTimerModeOn()){
                    System.out.println("Seconds left until turned on: " + cmp.getTimeLeft() / 1000);
                }
                else {
                    System.out.println("Seconds left until turned off: " + cmp.getTimeLeft() / 1000);
                }
            }
        }
    }

    public void print(String description){
        System.out.println(description);
    }
}
