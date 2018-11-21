package Holders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.com.beirario.pontuacaocampeonatos.R;

public class ClassificationHolder extends RecyclerView.ViewHolder{

    private TextView position;
    private TextView name;
    private CardView self;
    private TextView points;

    public TextView getName() {
        return name;
    }

    public TextView getPoints(){return points;}

    public TextView getPositionClassification(){return position;}

    public CardView getSelf() {
        return self;
    }

    public ClassificationHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.nameCard);
        self = itemView.findViewById(R.id.cardView);
        position = itemView.findViewById(R.id.positionCard);
        points = itemView.findViewById(R.id.pointsCard);
    }
}
