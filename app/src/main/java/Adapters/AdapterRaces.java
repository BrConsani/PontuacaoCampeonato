package Adapters;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import Holders.DefaultHolder;
import br.com.beirario.pontuacaocampeonatos.R;
import br.com.beirario.pontuacaocampeonatos.ViewRaces;

public class AdapterRaces extends RecyclerView.Adapter<DefaultHolder>{

    private final ViewRaces owner;

    public AdapterRaces(ViewRaces owner){
        this.owner = owner;
    }

    @NonNull
    @Override
    public DefaultHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DefaultHolder(LayoutInflater.from(viewGroup.getContext())

                .inflate(R.layout.card_race, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DefaultHolder holderStep, int i) {
        holderStep.getName().setText(owner.championship.getSteps().get(owner.indexStep).getRaces().get(i).getName());
        //holderStep.getSelf().setOnClickListener(view -> showPoints(i));
        holderStep.getSelf().setOnLongClickListener(view -> removeFromList(i));
    }

    @Override
    public int getItemCount() {
        return owner.championship.getSteps().get(owner.indexStep).getRaces() != null
                ? owner.championship.getSteps().get(owner.indexStep).getRaces().size() : 0;
    }

    private boolean removeFromList(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(owner);
        builder.setTitle(R.string.lb_erase_race);
        builder.setPositiveButton(R.string.button_erase, (arg0, arg1) -> {
            owner.championship.getSteps().get(owner.indexStep).getRaces().remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, owner.championship.getSteps().get(owner.indexStep).getRaces().size());
            owner.salvarObjeto();
        });
        builder.setNegativeButton(R.string.button_cancel, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return true;
    }

    private void showPoints(int position){
        //Implementar mudança de tela.
    }
}

