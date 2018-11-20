package br.com.beirario.pontuacaocampeonatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import Adapters.AdapterPilots;
import Models.Championship;
import Models.ManageLists;
import Models.Pilot;
import Repository.MainRepository;
import ViewModel.Dialogs;

public class ViewPilots extends AppCompatActivity implements ManageLists {

    public Championship championship;
    public int indexChampionship;

    private AdapterPilots cardViewAdapter;
    private RecyclerView cardsViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilots);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        championship = (Championship) getIntent().getSerializableExtra(getString(R.string.intent_championship));
        indexChampionship = getIntent().getIntExtra(getString(R.string.intent_indexC), 0);

        cardViewAdapter = new AdapterPilots(this);
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
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void btnCreatePilot(View view){
        Dialogs.dialogPickName(this, getString(R.string.title_dialog_pilot), getString(R.string.message_dialog_pilot));
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
