package Adapters;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import Holders.DefaultHolder;
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

    @Override
    public void onBindViewHolder(@NonNull DefaultHolder holderStep, int i) {
        holderStep.getName().setText(owner.championship.getPilots().get(owner.indexPilot).getDiscards().get(i).getName());
        holderStep.getSelf().setOnLongClickListener(view -> removeFromList(i));
    }

    @Override
    public int getItemCount() {
        return owner.championship.getPilots().get(owner.indexPilot).getDiscards() != null
                ? owner.championship.getPilots().get(owner.indexPilot).getDiscards().size() : 0;
    }

    private boolean removeFromList(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(owner);
        builder.setTitle(R.string.lb_erase_discard);
        builder.setPositiveButton(R.string.button_remove, (arg0, arg1) -> {
            owner.championship.getPilots().get(owner.indexPilot).getDiscards().remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, owner.championship.getPilots().get(owner.indexPilot).getDiscards().size());
            owner.salvarObjeto();
        });
        builder.setNegativeButton(R.string.button_cancel, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return true;
    }
}

