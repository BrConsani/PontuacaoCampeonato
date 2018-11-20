package Holders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.com.beirario.pontuacaocampeonatos.R;

public class RaceClassificationHolder extends RecyclerView.ViewHolder{

    private TextView position;
    private TextView name;
    private CardView self;
    private EditText points;

    public TextView getName() {
        return name;
    }

    public EditText getPoints(){return points;}

    public TextView getPositionClassification(){return position;}

    public CardView getSelf() {
        return self;
    }

    public RaceClassificationHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.nameCard);
        self = itemView.findViewById(R.id.cardView);
        position = itemView.findViewById(R.id.positionCard);
        points = itemView.findViewById(R.id.pointsCard);
    }
}
