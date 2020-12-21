package lab5;

public class TV extends Device {

    private int currChannel = 1;

    private String[] channels;

    public TV(EventListener eventListener, String name) {
        super(eventListener, name);
        initChannels();
    }

    private void initChannels(){
        channels = new String[5];

        channels[0] = "1+1";
        channels[1] = "Noviy";
        channels[2] = "Tet";
        channels[3] = "M1";
        channels[4] = "M2";
        
    }

    public String[] getChannels() {
        return channels;
    }

    public String getCurrChannel() {
        return channels[currChannel - 1];
    }

    public boolean setCurrChannel(int currChannel) {
        if (currChannel <= channels.length && isTurnedOn()){
            this.currChannel = currChannel;
            eventListener.update(name + "'s current channel has been changed to " +
                    currChannel + "(" + getCurrChannel() + ")");
            return true;
        }
        return false;
    }
}
