package br.com.beirario.pontuacaocampeonatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import Adapters.AdapterChampionship;
import Models.Championship;
import Models.ManageLists;
import Repository.MainRepository;
import ViewModel.Dialogs;

public class ViewChampionship extends AppCompatActivity implements ManageLists {

    public List<Championship> championships;

    private AdapterChampionship cardViewAdapter;
    private RecyclerView cardsViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(MainRepository.lerObjetoCompleto(this) != null){
            championships = MainRepository.lerObjetoCompleto(this);
        }else{
            championships = new ArrayList<>();
        }

        cardViewAdapter = new AdapterChampionship(championships, this);
        RecyclerView.LayoutManager cardViewManager = new LinearLayoutManager(this);
        cardsViewer = findViewById(R.id.contentView);
        cardsViewer.setLayoutManager(cardViewManager);
        cardsViewer.setAdapter(cardViewAdapter);
    }

    public void btnCreateChampionship(View view) {
        Dialogs.dialogPickName(this, getString(R.string.title_dialog_championship),
                                    getString(R.string.message_dialog_championship));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 0 && resultCode == RESULT_OK){
            Championship serializableObj = (Championship) data.getSerializableExtra(getString(R.string.intent_championship));
            championships.set(data.getIntExtra(getString(R.string.intent_indexC), 0), serializableObj);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void salvarObjeto(){
        MainRepository.salvarObjeto(this, championships);
    }

    @Override
    public void addObject(String name) {
        Championship championship = new Championship(name);
        championships.add(championship);
        cardViewAdapter.notifyItemInserted(cardViewAdapter.getItemCount());
        salvarObjeto();
    }
}
