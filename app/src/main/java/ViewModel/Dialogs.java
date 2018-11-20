package ViewModel;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Models.ManageLists;
import Models.Pilot;
import Models.Race;
import Models.RaceSteps;
import br.com.beirario.pontuacaocampeonatos.R;
import br.com.beirario.pontuacaocampeonatos.ViewDiscards;

import static Util.Converters.convertDpToPx;

public abstract class Dialogs {

    public static void dialogPickName(Context sender, String title, String message){
        final AlertDialog.Builder alert = new AlertDialog.Builder(sender);
        final EditText input = new EditText(sender);
        input.setSingleLine();
        FrameLayout container = new FrameLayout(sender);
        FrameLayout.LayoutParams params = new  FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = convertDpToPx(sender, 20);
        params.rightMargin = convertDpToPx(sender, 40);
        input.setLayoutParams(params);
        container.addView(input);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setView(container);
        alert.setPositiveButton(R.string.button_save, (dialog, which) -> {
            if(!input.getText().toString().equals("")){
                ((ManageLists)sender).addObject(input.getText().toString());
            }else{
                Toast.makeText(sender,"Você não digitou um nome válido!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

        });
        alert.show();
    }

    public static void dialogPickDiscards(Context sender, Pilot pilot, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(sender);
        builder.setTitle(title);

        List<Race> discards = new ArrayList<>();
        String[] discardsName = getAllDiscards(sender,pilot);

        builder.setMultiChoiceItems(discardsName, null, (dialog, which, isChecked) -> {
            if(isChecked){
                discards.add(getRace(sender, discardsName, which));
            }else{
                discards.remove(getRace(sender, discardsName, which));
            }
        });

        builder.setPositiveButton(R.string.button_save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((ViewDiscards) sender).championship.getPilots().get(((ViewDiscards) sender).indexPilot)
                        .setDiscards(discards);
                ((ViewDiscards) sender).notifyChange();
            }
        });

        builder.show();
    }

    private static String[] getAllDiscards(Context sender, Pilot pilot){
        int quantityRaces = 0;
        for (RaceSteps raceSteps: ((ViewDiscards) sender).championship.getSteps()) {
            for(Race race : raceSteps.getRaces()){
                if(race.getPilotsPosition().contains(pilot))
                    quantityRaces++;
            }
        }

        String[] names = new String[quantityRaces];
        int index = 0;
        for (RaceSteps raceSteps : ((ViewDiscards) sender).championship.getSteps()) {
            for(Race race : raceSteps.getRaces()){
                if(race.getPilotsPosition().contains(pilot)) {
                    names[index] = raceSteps.getName() + " - " + race.getName()
                            + " - pts: "
                            + race.getPointsPosition().get(race.getPilotsPosition().indexOf(pilot));
                    index++;
                }
            }
        }
        return names;
    }

    private static Race getRace(Context sender, String[] names, int i){
        ViewDiscards owner = (ViewDiscards) sender;
        String[] split = names[i].split(" - ");
        String nameStep = split[0];
        String nameRace = split[1];
        int indexStep = owner.championship.getSteps().indexOf(new RaceSteps(nameStep));
        int indexRace = owner.championship.getSteps().get(indexStep).getRaces().indexOf(new Race(nameRace));
        return owner.championship.getSteps().get(indexStep).getRaces().get(indexRace);
    }
}
