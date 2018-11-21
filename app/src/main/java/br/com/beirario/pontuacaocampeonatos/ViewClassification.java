package br.com.beirario.pontuacaocampeonatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Adapters.AdapterClassification;
import Adapters.AdapterRaceClassification;
import Models.Championship;
import Models.Pilot;
import Models.Race;
import Models.RaceSteps;
import Repository.MainRepository;

public class ViewClassification extends AppCompatActivity{

    public Championship championship;
    public int indexChampionship;
    public List<Pilot> classificationPilots = new ArrayList<>();

    private AdapterClassification cardViewAdapter;
    private RecyclerView cardsViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        championship = (Championship) getIntent().getSerializableExtra(getString(R.string.intent_championship));
        indexChampionship = getIntent().getIntExtra(getString(R.string.intent_indexC), 0);

        cardViewAdapter = new AdapterClassification(this);
        RecyclerView.LayoutManager cardViewManager = new LinearLayoutManager(this);
        cardsViewer = findViewById(R.id.contentView);
        cardsViewer.setLayoutManager(cardViewManager);
        cardsViewer.setAdapter(cardViewAdapter);

        sumPointsWithoutDiscard();

        Switch btn = findViewById(R.id.btnUseDiscard);
        btn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                sumPointsWithDiscard();
            }else{
                sumPointsWithoutDiscard();
            }
        });
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

    public void salvarObjeto(){
        MainRepository.salvarObjeto(this, championship, indexChampionship);
    }

    private void sumPointsWithoutDiscard(){

        classificationPilots = new ArrayList<>();

        for(Pilot pilot: this.championship.getPilots()){
            int points = 0;
            for (RaceSteps raceSteps : this.championship.getSteps()) {
                if(raceSteps.getPolePosition().equals(pilot))
                    points++;
                for(Race race : raceSteps.getRaces()){
                    if(race.getPilotsPosition().contains(pilot)) {
                        points += race.getPointsPosition().get(race.getPilotsPosition().indexOf(pilot));
                    }
                }
            }
           pilot.setPoints(points);
            classificationPilots.add(pilot);
        }
        Collections.sort(classificationPilots);
        cardViewAdapter.notifyDataSetChanged();
    }

    private void sumPointsWithDiscard(){

        classificationPilots = new ArrayList<>();

        for(Pilot pilot: this.championship.getPilots()){
            int points = 0;
            for (RaceSteps raceSteps : this.championship.getSteps()) {
                if(raceSteps.getPolePosition().equals(pilot))
                    points++;
                for(Race race : raceSteps.getRaces()){
                    if(race.getPilotsPosition().contains(pilot)) {
                        points += race.getPointsPosition().get(race.getPilotsPosition().indexOf(pilot));
                    }
                }
            }
            pilot.setPoints(points);
            for(Race race: pilot.getDiscards()){
                pilot.addPoints(-race.getPointsPosition().get(race.getPilotsPosition().indexOf(pilot)));
            }
            classificationPilots.add(pilot);
    }
        Collections.sort(classificationPilots);
        cardViewAdapter.notifyDataSetChanged();
    }
}
