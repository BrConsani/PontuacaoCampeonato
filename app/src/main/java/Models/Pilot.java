package Models;

import java.io.Serializable;

public class Pilot implements Serializable {

    private String name;
    private int points;

    public Pilot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points){
        this.points += points;
    }

}
