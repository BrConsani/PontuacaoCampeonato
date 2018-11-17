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

import Adapters.AdapterStep;
import Models.Championship;
import Models.ManageLists;
import Models.RaceSteps;
import Repository.MainRepository;
import ViewModel.Dialogs;

public class ViewSteps extends AppCompatActivity implements ManageLists {

    public Championship championship;
    public int indexChampionship;

    private AdapterStep cardViewAdapter;
    private RecyclerView cardsViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        championship = (Championship) getIntent().getSerializableExtra(getString(R.string.intent_championship));
        indexChampionship = getIntent().getIntExtra(getString(R.string.intent_indexC), 0);

        cardViewAdapter = new AdapterStep(this);
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
            case R.id.navigation_pilots:
                Intent intentP = new Intent(this, ViewPilots.class);
                intentP.putExtra(getString(R.string.intent_championship), championship);
                intentP.putExtra(getString(R.string.intent_indexC), indexChampionship);
                startActivityForResult(intentP, 0);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_racesteps, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void btnCreateRaceSteps(View view){
        Dialogs.dialogPickName(this, getString(R.string.title_dialog_racesteps), getString(R.string.message_dialog_racesteps));
    }

    public void salvarObjeto(){
        MainRepository.salvarObjeto(this, championship, indexChampionship);
    }

    @Override
    public void addObject(String name) {
        RaceSteps step = new RaceSteps(name);
        championship.addStep(step);
        cardViewAdapter.notifyItemInserted(cardViewAdapter.getItemCount());
        salvarObjeto();
    }
}
