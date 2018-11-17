package br.com.beirario.pontuacaocampeonatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import Adapters.AdapterRaces;
import Adapters.AdapterStep;
import Models.Championship;
import Models.ManageLists;
import Models.Race;
import Models.RaceSteps;
import Repository.MainRepository;
import ViewModel.Dialogs;

public class ViewRaces extends AppCompatActivity implements ManageLists {

    public Championship championship;
    public int indexChampionship;
    public int indexStep;

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

    public void btnCreateRace(View view){
        Dialogs.dialogPickName(this, getString(R.string.title_dialog_race), getString(R.string.message_dialog_race));
    }

    public void salvarObjeto(){
        MainRepository.salvarObjeto(this, championship, indexChampionship);
    }

    @Override
    public void addObject(String name) {
        Race race = new Race(name);
        championship.getSteps().get(indexStep).getRaces().add(race);
        cardViewAdapter.notifyItemInserted(cardViewAdapter.getItemCount());
        salvarObjeto();
    }
}
