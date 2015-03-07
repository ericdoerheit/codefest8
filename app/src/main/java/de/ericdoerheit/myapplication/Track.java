package de.ericdoerheit.myapplication;

/**
 * Created by ericdorheit on 07.03.15.
 */
public class Track {
    private int speed;
    private long timestamp;

    public Track(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
