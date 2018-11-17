package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RaceSteps implements Serializable {

    private String name;
    private List<Race> races = new ArrayList<>();

    public RaceSteps(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Race> getRaces(){return races;}

    public void addRaces(Race race){races.add(race);}
}
