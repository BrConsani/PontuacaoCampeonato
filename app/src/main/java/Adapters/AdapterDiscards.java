package Adapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import Holders.DefaultHolder;
import Models.Race;
import Models.RaceSteps;
import br.com.beirario.pontuacaocampeonatos.R;
import br.com.beirario.pontuacaocampeonatos.ViewDiscards;

public class AdapterDiscards extends RecyclerView.Adapter<DefaultHolder>{

    private final ViewDiscards owner;

    public AdapterDiscards(ViewDiscards owner){
        this.owner = owner;
    }

    @NonNull
    @Override
    public DefaultHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DefaultHolder(LayoutInflater.from(viewGroup.getContext())

                .inflate(R.layout.card_discard, viewGroup, false));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull DefaultHolder holderStep, int i) {
        holderStep.getName().setText(String.format("%s - %s - %dpts",
                owner.championship.getPilots().get(owner.indexPilot).getDiscards().get(i).getStep(),
                owner.championship.getPilots().get(owner.indexPilot).getDiscards().get(i).getName(),
                getPoints(i)));
    }

    @Override
    public int getItemCount() {
        return owner.championship.getPilots().get(owner.indexPilot).getDiscards() != null
                ? owner.championship.getPilots().get(owner.indexPilot).getDiscards().size() : 0;
    }

    private int getPoints(int position){
        int indexStep = owner.championship.getSteps().indexOf(
                new RaceSteps(owner.championship.getPilots().get(owner.indexPilot).getDiscards().get(position).getStep()));
        int indexRace = owner.championship.getSteps().get(indexStep).getRaces().indexOf(
                new Race(owner.championship.getPilots().get(owner.indexPilot).getDiscards().get(position).getName()));
        int indexPilot = owner.championship.getSteps().get(indexStep).getRaces().get(indexRace)
                .getPilotsPosition().indexOf(owner.championship.getPilots().get(owner.indexPilot));
        return owner.championship.getSteps().get(indexStep).getRaces().get(indexRace).getPointsPosition().get(indexPilot);
    }
}

