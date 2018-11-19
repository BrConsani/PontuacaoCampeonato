package br.com.beirario.pontuacaocampeonatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import Adapters.AdapterRaceClassification;
import Models.Championship;
import Models.Pilot;
import Repository.MainRepository;

public class ViewRaceClassification extends AppCompatActivity{

    public Championship championship;
    public int indexChampionship;
    public int indexStep;
    public int indexRace;

    private AdapterRaceClassification cardViewAdapter;
    private RecyclerView cardsViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_classification);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        championship = (Championship) getIntent().getSerializableExtra(getString(R.string.intent_championship));
        indexChampionship = getIntent().getIntExtra(getString(R.string.intent_indexC), 0);
        indexStep = getIntent().getIntExtra(getString(R.string.intent_indexS), 0);
        indexRace = getIntent().getIntExtra(getString(R.string.intent_indexR), 0);

        cardViewAdapter = new AdapterRaceClassification(this);
        RecyclerView.LayoutManager cardViewManager = new LinearLayoutManager(this);
        cardsViewer = findViewById(R.id.contentView);
        cardsViewer.setLayoutManager(cardViewManager);
        cardsViewer.setAdapter(cardViewAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 0 && resultCode == RESULT_OK){
            championship = (Championship) data.getSerializableExtra(getString(R.string.intent_championship));
        }
        super.onActivityResult(requestCode, resultCode, data);
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
            case R.id.navigation_points:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_race_points, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void btnCreateRaceClassification(View view){
        addObject();
    }

    public void btnRemoveRaceClassification(View view){
        removeObject();
    }

    public void salvarObjeto(){
        MainRepository.salvarObjeto(this, championship, indexChampionship);
    }

    public void addObject() {
        championship.getSteps().get(indexStep).getRaces().get(indexRace).getPilotsPosition().add(new Pilot(""));
        cardViewAdapter.notifyItemInserted(cardViewAdapter.getItemCount());
        salvarObjeto();
    }

    public void removeObject() {
        int size = championship.getSteps().get(indexStep).getRaces().get(indexRace).getPilotsPosition().size();

        if(size != 0){
            championship.getSteps().get(indexStep).getRaces().get(indexRace).getPilotsPosition().remove((size-1));
            cardViewAdapter.notifyItemRemoved(cardViewAdapter.getItemCount());
            salvarObjeto();
        }else{
            Toast.makeText(this, getText(R.string.toast_error_race_classification), Toast.LENGTH_SHORT).show();
        }

    }


}
