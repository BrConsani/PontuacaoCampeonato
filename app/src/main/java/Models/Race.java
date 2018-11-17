package Models;

import java.io.Serializable;

public class Race implements Serializable {

    private String name;

    public Race(String name){
        this.name = name;
    }

    public String getName(){ return name;}

}
