package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Race implements Serializable {

    private String name;
    private List<Pilot> pilotsPosition = new ArrayList<>();
    private List<Integer> pointsPosition = new ArrayList<>();

    public Race(String name){
        this.name = name;
    }

    public String getName(){ return name;}

    public void addPilot(Pilot pilot, int position){pilotsPosition.set(position, pilot);}

    public void addPoints(int points, int position){pointsPosition.set(position, points);}

    public List<Pilot> getPilotsPosition(){return pilotsPosition;}

    public List<Integer> getPointsPosition(){return pointsPosition;}
}
