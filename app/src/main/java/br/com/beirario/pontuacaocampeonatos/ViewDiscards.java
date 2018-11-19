package br.com.beirario.pontuacaocampeonatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import Adapters.AdapterDiscards;
import Models.Championship;
import Models.ManageLists;
import Models.Pilot;
import Repository.MainRepository;
import ViewModel.Dialogs;

public class ViewDiscards extends AppCompatActivity implements ManageLists {

    public Championship championship;
    public int indexChampionship;
    public int indexPilot;

    private AdapterDiscards cardViewAdapter;
    private RecyclerView cardsViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discards);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        championship = (Championship) getIntent().getSerializableExtra(getString(R.string.intent_championship));
        indexChampionship = getIntent().getIntExtra(getString(R.string.intent_indexC), 0);
        indexPilot = getIntent().getIntExtra(getString(R.string.intent_indexD), 0);

        cardViewAdapter = new AdapterDiscards(this);
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

    public void btnCreatePilot(View view){
        Dialogs.dialogPickDiscards(this, championship.getPilots().get(indexPilot),
                getString(R.string.title_dialog_discard),
                getString(R.string.message_dialog_discard));
    }

    public void salvarObjeto(){
        MainRepository.salvarObjeto(this, championship, indexChampionship);
    }

    @Override
    public void addObject(String name) {
        Pilot pilot = new Pilot(name);
        championship.getPilots().add(pilot);
        cardViewAdapter.notifyItemInserted(cardViewAdapter.getItemCount());
        salvarObjeto();
    }
}
