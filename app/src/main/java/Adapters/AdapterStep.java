package Adapters;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import Holders.DefaultHolder;
import br.com.beirario.pontuacaocampeonatos.R;
import br.com.beirario.pontuacaocampeonatos.ViewRaces;
import br.com.beirario.pontuacaocampeonatos.ViewSteps;

public class AdapterStep extends RecyclerView.Adapter<DefaultHolder>{

    private final ViewSteps owner;

    public AdapterStep(ViewSteps owner){
        this.owner = owner;
    }

    @NonNull
    @Override
    public DefaultHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DefaultHolder(LayoutInflater.from(viewGroup.getContext())

                .inflate(R.layout.card_step, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DefaultHolder holderStep, int i) {
        holderStep.getName().setText(owner.championship.getSteps().get(i).getName());
        holderStep.getSelf().setOnClickListener(view -> showRaces(i));
        holderStep.getSelf().setOnLongClickListener(view -> removeFromList(i));
    }

    @Override
    public int getItemCount() {
        return owner.championship.getSteps() != null ? owner.championship.getSteps().size() : 0;
    }

    private boolean removeFromList(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(owner);
        builder.setTitle(R.string.lb_erase_race_steps);
        builder.setPositiveButton(R.string.button_erase, (arg0, arg1) -> {
            owner.championship.getSteps().remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, owner.championship.getSteps().size());
            owner.salvarObjeto();
        });
        builder.setNegativeButton(R.string.button_cancel, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return true;
    }

    private void showRaces(int position){
        Intent intent = new Intent(owner, ViewRaces.class);
        intent.putExtra(owner.getString(R.string.intent_championship), owner.championship);
        intent.putExtra(owner.getString(R.string.intent_indexC), owner.indexChampionship);
        intent.putExtra(owner.getString(R.string.intent_indexS), position);
        owner.startActivityForResult(intent, 0);
    }
}

