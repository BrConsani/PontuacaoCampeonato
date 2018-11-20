package Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import Holders.RaceClassificationHolder;
import Models.Pilot;
import br.com.beirario.pontuacaocampeonatos.R;
import br.com.beirario.pontuacaocampeonatos.ViewRaceClassification;

public class AdapterRaceClassification extends RecyclerView.Adapter<RaceClassificationHolder>{

    private final ViewRaceClassification owner;

    public AdapterRaceClassification(ViewRaceClassification owner){
        this.owner = owner;
    }

    @NonNull
    @Override
    public RaceClassificationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RaceClassificationHolder(LayoutInflater.from(viewGroup.getContext())

                .inflate(R.layout.card_race_classification, viewGroup, false));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull RaceClassificationHolder holderStep, int i) {
        holderStep.getName().setText(owner.championship.getSteps().get(owner.indexStep)
                                                                        .getRaces().get(owner.indexRace)
                                                                        .getPilotsPosition().get(i).getName());
        holderStep.getPositionClassification().setText(String.format("%dº", (i+1)));
        holderStep.getSelf().setOnClickListener(view -> changePilot(i));

        holderStep.getPoints().setText(String.format("%d", Integer.parseInt(owner.championship.getSteps().get(owner.indexStep)
                                                            .getRaces().get(owner.indexRace)
                                                            .getPointsPosition().get(i).toString())));
        holderStep.getPoints().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                owner.championship.getSteps().get(owner.indexStep)
                        .getRaces().get(owner.indexRace)
                        .getPointsPosition().set(i,
                        !holderStep.getPoints().getText().toString().equals("") ?
                                Integer.parseInt(holderStep.getPoints().getText().toString()):null);
                owner.salvarObjeto();
            }
        });
    }

    @Override
    public int getItemCount() {
        return owner.championship.getSteps().get(owner.indexStep).getRaces().get(owner.indexRace)
                .getPilotsPosition() != null ? owner.championship.getSteps().get(owner.indexStep)
                .getRaces().get(owner.indexRace)
                .getPilotsPosition().size() : 0;
    }

    private void changePilot(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(owner);
        builder.setTitle(owner.getString(R.string.title_dialog_race_classification));

        builder.setItems(nomesPilotos(), (arg0, arg1) -> {
            owner.championship.getSteps().get(owner.indexStep).getRaces().get(owner.indexRace).
                    getPilotsPosition().set(position, owner.championship.getPilots().get(arg1));
            notifyItemChanged(position);
            owner.salvarObjeto();
        });

        builder.show();
    }

    private String[] nomesPilotos(){
        String[] names = new String[owner.championship.getPilots().size()];
        int index = 0;
        for (Pilot p : owner.championship.getPilots()) {
            names[index] = p.getName();
            index++;
        }
        return names;
    }
}

