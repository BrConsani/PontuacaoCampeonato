package Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import Holders.ClassificationHolder;
import Holders.RaceClassificationHolder;
import Models.Pilot;
import Models.Race;
import Models.RaceSteps;
import br.com.beirario.pontuacaocampeonatos.R;
import br.com.beirario.pontuacaocampeonatos.ViewClassification;
import br.com.beirario.pontuacaocampeonatos.ViewDiscards;
import br.com.beirario.pontuacaocampeonatos.ViewRaceClassification;

public class AdapterClassification extends RecyclerView.Adapter<ClassificationHolder>{

    private final ViewClassification owner;

    public AdapterClassification(ViewClassification owner){
        this.owner = owner;
    }

    @NonNull
    @Override
    public ClassificationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ClassificationHolder(LayoutInflater.from(viewGroup.getContext())

                .inflate(R.layout.card_classification, viewGroup, false));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ClassificationHolder holderStep, int i) {
        holderStep.getName().setText(owner.classificationPilots.get(i).getName());
        holderStep.getPoints().setText(String.format("%d", owner.classificationPilots.get(i).getPoints()));
        holderStep.getPositionClassification().setText(String.format("%dº", (i+1)));
    }

    @Override
    public int getItemCount() {
        return owner.championship.getPilots() != null ? owner.championship.getPilots().size() : 0;
    }
}

