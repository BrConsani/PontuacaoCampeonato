package Adapters;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import Holders.DefaultHolder;
import br.com.beirario.pontuacaocampeonatos.R;
import br.com.beirario.pontuacaocampeonatos.ViewPilots;
import br.com.beirario.pontuacaocampeonatos.ViewRaces;

public class AdapterPilots extends RecyclerView.Adapter<DefaultHolder>{

    private final ViewPilots owner;

    public AdapterPilots(ViewPilots owner){
        this.owner = owner;
    }

    @NonNull
    @Override
    public DefaultHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DefaultHolder(LayoutInflater.from(viewGroup.getContext())

                .inflate(R.layout.card_pilot, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DefaultHolder holderStep, int i) {
        holderStep.getName().setText(owner.championship.getPilots().get(i).getName());
        //holderStep.getSelf().setOnClickListener(view -> showDiscards(i));
        holderStep.getSelf().setOnLongClickListener(view -> removeFromList(i));
    }

    @Override
    public int getItemCount() {
        return owner.championship.getPilots() != null
                ? owner.championship.getPilots().size() : 0;
    }

    private boolean removeFromList(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(owner);
        builder.setTitle(R.string.lb_erase_pilot);
        builder.setPositiveButton(R.string.button_erase, (arg0, arg1) -> {
            owner.championship.getPilots().remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, owner.championship.getPilots().size());
            owner.salvarObjeto();
        });
        builder.setNegativeButton(R.string.button_cancel, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return true;
    }

    private void showDiscards(int position){
        //Implementar mudança de tela.
    }
}

