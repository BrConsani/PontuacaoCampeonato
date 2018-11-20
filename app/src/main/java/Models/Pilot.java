package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pilot implements Serializable {

    private String name;
    private int points;
    private List<Race> races = new ArrayList<>();
    private List<Race> discards = new ArrayList<>();

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

    public List<Race> getDiscards(){return discards;}

    public void setDiscards(List<Race> discards){this.discards = discards;}

    public void addDiscards(Race discard){ discards.add(discard);}

    public List<Race> getRaces(){return races;}

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;

        if(((Pilot) obj).getName().equals(this.getName())){
            equal = true;
        }
        return equal;
    }
}
