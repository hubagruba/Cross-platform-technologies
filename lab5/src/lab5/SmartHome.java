package lab5;
import java.util.ArrayList;

public class SmartHome implements EventListener {

    private int id_tv = 0;
    private int id_l = 0;
    private int id_f = 0;
    private int id_c = 0;
    private int id_t = 0;

    private Actions actions;
    private ArrayList<Device> components;

    {
        actions =  new Actions();
        components = new ArrayList<>();
    }

    private boolean addComponent(String device) {
    	Device newDevice;
        switch (device) {
            case "TV":
                newDevice = new TV(this, "TV" + ++id_tv);
                break;
            case "Light":
                newDevice = new Light(this,"Light" + ++id_l);
                break;
            case "Teapot":
                newDevice = new Light(this,"Teapot" + ++id_t);
                break;
            case "Fridge":
                newDevice = new Fridge(this,"Fridge" + ++id_f);
                break;
            case "Conditioner":
                newDevice = new Conditioner(this,"Conditioner" + ++id_c);
                break;
            default:
                return false;
        }
        components.add(newDevice);
        actions.print(newDevice.getName() + " has been added");
        
        return true;
    }

    private boolean removeComponent(String name) {
        Device remove = null;
        for (Device component : components) {
            if (component.getName().equals(name)) {
                remove = component;
            }
        }
        if (remove != null) {
            components.remove(remove);
            actions.print(name + " has been removed");
            return true;
        }
        return false;
    }

    private Device findComponentByName(String name){
        for (Device cmp : components) {
            if (cmp.getName().equals(name)) {
                return cmp;
            }
        }
        return null;
    }

    public void performCommand(String cmd){
        if (!actions.isCommandValid(cmd)){
            actions.printInfoAboutSuccess(false);
            return;
        }
        String[] devices = cmd.split(" ");
        String action = devices[0];

        boolean succeed = true;
        
    
        
       
        switch(action){
            case "add":
                String type = devices[1];
                succeed = addComponent(type);
                break;
            case "remove":
                String name = devices[1];
                succeed = removeComponent(name);
                break;
            case "turnon":
                name = devices[1];
                Device device = findComponentByName(name);
                if (device != null){
                    succeed = device.turnOn();
                }
                break;
            case "turnoff":
                name = devices[1];
                device = findComponentByName(name);
                if (device != null){
                    succeed = device.turnOff();
                }
                break;
            case "timeron":
                name = devices[1];
                device = findComponentByName(name);
                if (device != null){
                    int seconds = Integer.parseInt(devices[2]);
                    succeed = device.startTimer(true, seconds);
                }
                break;
            case "timeroff":
                name = devices[1];
                device = findComponentByName(name);
                if (device != null){
                    int seconds = Integer.parseInt(devices[2]);
                    succeed = device.startTimer(false, seconds);
                }
                break;
            case "temp+":
                name = devices[1];
                device = findComponentByName(name);
                if (device != null){
                    float value = Float.valueOf(devices[2]);
                        succeed = ((Conditioner)device).increaseTemp(value);
                        
                }
                break;
            case "temp-":
                name = devices[1];
                device = findComponentByName(name);
                if (device != null){
                    float value = Float.valueOf(devices[2]);
                        succeed = ((Conditioner)device).decreaseTemp(value);
  
                }
                break;
            case "channels":
                name = devices[1];
                device = findComponentByName(name);
                if (device != null && device.getClass().equals(TV.class) ) {
                    String[] channels = ((TV) device).getChannels();
                    actions.printStrArr(channels, "Channel list for " + device.getName() + ":");
                }
                break;
            case "setchannel":
                name = devices[1];
                device = findComponentByName(name);
                if (device != null && device.getClass().equals(TV.class) ) {
                    int channel = Integer.parseInt(devices[2]);
                    succeed = ((TV) device).setCurrChannel(channel);
                }
                break;
            case "info":
                actions.printDetailedInformation(components);
                break;
           
        }
        actions.printInfoAboutSuccess(succeed);
    }

    public void update(String description) {
        actions.print(description);
    }
}
