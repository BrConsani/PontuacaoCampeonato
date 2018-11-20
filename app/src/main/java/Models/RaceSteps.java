package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RaceSteps implements Serializable {

    private String name;
    private List<Race> races = new ArrayList<>();
    private Pilot polePosition;

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

    public Pilot getPolePosition(){return polePosition;}

    public void setPolePosition(Pilot pilot){this.polePosition = pilot;}

    public void addRaces(Race race){races.add(race);}

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;

        if(((RaceSteps) obj).getName().equals(this.getName())){
            equal = true;
        }
        return equal;
    }
}
