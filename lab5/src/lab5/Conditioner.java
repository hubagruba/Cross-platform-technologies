package lab5;

public class Conditioner extends Device {

    private int minTemp = 0;
    private int maxTemp = 40;
    private float temp = 24.0f;

    public Conditioner(EventListener eventListener, String name){
        super(eventListener, name);
    }

    public boolean increaseTemp(float inc){
        if (temp + inc < maxTemp && isTurnedOn()){
            temp += inc;
            eventListener.update(name + "'s temperature has been increased to " + temp);
            return true;
        }
        return false;
    }

    public boolean decreaseTemp(float dec){
        if (temp - dec > minTemp && isTurnedOn()){
            temp -= dec;
            eventListener.update(name + "'s temperature has been decreased to " + temp);
            return true;
        }
        return false;
    }

    public float getTemp() {
        return temp;
    }
}
