package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Race implements Serializable {

    private String name;
    private String stepName;
    private List<Pilot> pilotsPosition = new ArrayList<>();
    private List<Integer> pointsPosition = new ArrayList<>();

    public Race(String name){
        this.name = name;
    }

    public String getName(){ return name;}

    public String getStep(){return stepName;}

    public void setStep(RaceSteps step){this.stepName = step.getName();}

    public void addPilot(Pilot pilot, int position){pilotsPosition.set(position, pilot);}

    public void addPoints(int points, int position){pointsPosition.set(position, points);}

    public List<Pilot> getPilotsPosition(){return pilotsPosition;}

    public List<Integer> getPointsPosition(){return pointsPosition;}



    @Override

    public boolean equals(Object obj) {
        boolean equal = false;

        if(((Race) obj).getName().equals(this.getName())){
            equal = true;
        }
        return equal;
    }
}
