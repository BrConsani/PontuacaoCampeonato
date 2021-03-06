package br.com.beirario.pontuacaocampeonatos;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import Adapters.AdapterRaces;
import Models.Championship;
import Models.ManageLists;
import Models.Pilot;
import Models.Race;
import Repository.MainRepository;
import ViewModel.Dialogs;

public class ViewRaces extends AppCompatActivity implements ManageLists {

    public Championship championship;
    public int indexChampionship;
    public int indexStep;
    public CardView poleCard;

    private AdapterRaces cardViewAdapter;
    private RecyclerView cardsViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_races);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        championship = (Championship) getIntent().getSerializableExtra(getString(R.string.intent_championship));
        indexChampionship = getIntent().getIntExtra(getString(R.string.intent_indexC), 0);
        indexStep = getIntent().getIntExtra(getString(R.string.intent_indexS), 0);

        poleCard = findViewById(R.id.poleView);
        poleCard.setOnClickListener(v -> changePilot());

        cardViewAdapter = new AdapterRaces(this);
        RecyclerView.LayoutManager cardViewManager = new LinearLayoutManager(this);
        cardsViewer = findViewById(R.id.contentView);
        cardsViewer.setLayoutManager(cardViewManager);
        cardsViewer.setAdapter(cardViewAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra(getString(R.string.intent_championship), championship);
                intent.putExtra(getString(R.string.intent_indexC), indexChampionship);
                setResult(RESULT_OK,intent);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 0 && resultCode == RESULT_OK){
            championship = (Championship) data.getSerializableExtra(getString(R.string.intent_championship));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void btnCreateRace(View view){
        Dialogs.dialogPickName(this, getString(R.string.title_dialog_race), getString(R.string.message_dialog_race));
    }

    public void salvarObjeto(){
        MainRepository.salvarObjeto(this, championship, indexChampionship);
    }

    @Override
    public void addObject(String name) {
        Race race = new Race(name);
        race.setStep(championship.getSteps().get(indexStep));
        championship.getSteps().get(indexStep).getRaces().add(race);
        cardViewAdapter.notifyItemInserted(cardViewAdapter.getItemCount());
        salvarObjeto();
    }

    private void changePilot(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(this.getString(R.string.title_dialog_step_pole_position));

        builder.setItems(nomesPilotos(), (arg0, arg1) -> {
            this.championship.getSteps().get(this.indexStep).setPolePosition(this.championship.getPilots().get(arg1));
            TextView txt = poleCard.findViewById(R.id.nameCard);
            txt.setText(championship.getSteps().get(indexStep).getPolePosition().getName());
            this.salvarObjeto();
        });

        builder.show();
    }

    private String[] nomesPilotos(){
        String[] names = new String[this.championship.getPilots().size()];
        int index = 0;
        for (Pilot p : this.championship.getPilots()) {
            names[index] = p.getName();
            index++;
        }
        return names;
    }
}
