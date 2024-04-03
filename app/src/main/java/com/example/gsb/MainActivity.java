package com.example.gsb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.gsb.utils.BdAdapter;


public class MainActivity extends AppCompatActivity {

    Button addEchantillonButton;
    Button updateEchantillonButton;
    Button listEchantillonButton;
    Button buttonQuitterMenu;
    static final int VERSION_BDD = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // supression de la base de donnée
        // deleteDatabase("gsb.db");


        addEchantillonButton = (Button)findViewById(R.id.menuButtonAjoutEchantillon);
        updateEchantillonButton = (Button)findViewById(R.id.menuButtonMajEchantillons);
        listEchantillonButton = (Button)findViewById(R.id.menuButtonListeEchantillons);
        buttonQuitterMenu = (Button)findViewById(R.id.buttonQuitterMenu);

        addEchantillonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, addEchantillon.class);
                startActivity(intent);
            }
        });

        updateEchantillonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, majEchantillon.class);
                startActivity(intent);
            }
        });

        listEchantillonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, listeEchantillon.class);
                startActivity(intent);
            }
        });

        buttonQuitterMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.exit(1);
            }
        });


    }

    public void jeuEssaiBd(){
        //Création d'une instance de la classe echantBDD
        BdAdapter echantBdd = new BdAdapter(this);
        //On ouvre la base de données pour écrire dedans
        echantBdd.open();
        //On insère DES ECHANTILLONS DANS LA BD
        echantBdd.insererEchantillon(new Echantillon("code1", "lib1", "3"));
        echantBdd.insererEchantillon(new Echantillon("code2", "lib2", "5"));
        echantBdd.insererEchantillon(new Echantillon("code3", "lib3", "7"));
        echantBdd.insererEchantillon(new Echantillon("code4", "lib4", "6"));
        Cursor unCurseur = echantBdd.getDataEchantillons();
        System.out.println("il y a "+String.valueOf(unCurseur.getCount())+" echantillons dans la BD");
        echantBdd.close();
    }
// je peux supprimer :
    public boolean onCreateOptionsMenu(Menu menu) {
        // Gonflez le menu; cela ajoute des éléments à la barre d'action si elle est présente.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Gérer l'élément de la barre d'action clique ici. La barre d'action
        // gère automatiquement les clics sur le bouton Accueil/Haut, tant pis
        // lorsque vous spécifiez une activité parent dans AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // L'action pour l'élément "Settings" est déclenchée ici
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}