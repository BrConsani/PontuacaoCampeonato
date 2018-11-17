package Holders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.beirario.pontuacaocampeonatos.R;

public class DefaultHolder extends RecyclerView.ViewHolder{

    private TextView name;
    private CardView self;

    public TextView getName() {
        return name;
    }

    public CardView getSelf() {
        return self;
    }

    public DefaultHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.nameCard);
        self = itemView.findViewById(R.id.cardView);
    }
}
