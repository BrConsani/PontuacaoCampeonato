package Adapters;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import Holders.DefaultHolder;
import Models.Championship;
import br.com.beirario.pontuacaocampeonatos.R;
import br.com.beirario.pontuacaocampeonatos.ViewChampionship;
import br.com.beirario.pontuacaocampeonatos.ViewSteps;

public class AdapterChampionship extends RecyclerView.Adapter<DefaultHolder>{

    private final List<Championship> championships;
    private final ViewChampionship owner;

    public AdapterChampionship(List<Championship> championships, ViewChampionship owner){
        this.championships = championships;
        this.owner = owner;
    }

    @NonNull
    @Override
    public DefaultHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DefaultHolder(LayoutInflater.from(viewGroup.getContext())

                .inflate(R.layout.card_championship, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DefaultHolder defaultHolder, int i) {
        defaultHolder.getName().setText(championships.get(i).getName());
        defaultHolder.getSelf().setOnClickListener(view -> showRaceSteps(i));
        defaultHolder.getSelf().setOnLongClickListener(view -> removeFromList(i));
    }

    @Override
    public int getItemCount() {
        return championships != null ? championships.size() : 0;
    }

    private boolean removeFromList(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(owner);
        builder.setTitle(R.string.lb_erase_championship);
        builder.setPositiveButton(R.string.button_erase, (arg0, arg1) -> {
            owner.championships.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, owner.championships.size());
            owner.salvarObjeto();
        });
        builder.setNegativeButton(R.string.button_cancel, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return true;
    }

    private void showRaceSteps(int position){
        Intent intent = new Intent(owner, ViewSteps.class);
        intent.putExtra(owner.getString(R.string.intent_championship), championships.get(position));
        intent.putExtra(owner.getString(R.string.intent_indexC), position);
        owner.startActivityForResult(intent, 0);
    }
}
