package com.example.gsb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gsb.utils.BdAdapter;

public class listeEchantillon extends AppCompatActivity {
    private ListView listViewEchant;

    private BdAdapter echantBdd;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.liste_echantillon);

        listViewEchant = (ListView) findViewById(R.id.ListeListViewEchant);

        echantBdd = new BdAdapter(this);

        echantBdd.open();

        Cursor leCurseur = echantBdd.getDataEchantillons();

        String[] colNoms = new String[] {BdAdapter.COL_CODE_ECHANT, BdAdapter.COL_LIB, BdAdapter.COL_STOCK};

        int[] colNumeros = new int[] {R.id.listeTextViewCode, R.id.listeTextViewLib, R.id.listeTextViewStock};

        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(this, R.layout.liste_entree, leCurseur,colNoms,colNumeros);

        listViewEchant.setAdapter(dataAdapter);

        echantBdd.close();

        // Assurez-vous d'avoir un bouton dans votre layout XML avec l'ID "buttonQuitterListe"
        findViewById(R.id.buttonQuitterListe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Terminer cette activité et retourner à l'activité précédente (MainActivity)
                finish();
            }
        });

    }
}





