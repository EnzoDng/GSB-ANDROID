package com.example.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gsb.utils.BdAdapter;

public class addEchantillon extends AppCompatActivity {

    private EditText editTextCode;
    private EditText editTextLibelle;
    private EditText editTextStock;
    private TextView textViewMessageMAJEchantillon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_echantillon);



        // Assurez-vous d'avoir un bouton dans votre layout XML avec l'ID "buttonQuitterListe"
        findViewById(R.id.buttonQuitterAjout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Terminer cette activité et retourner à l'activité précédente (MainActivity)
                finish();
            }

        });
        BdAdapter database = new BdAdapter(this);
        editTextCode = findViewById(R.id.editTextCode);
        editTextLibelle = findViewById(R.id.editTextLibelle);
        editTextStock = findViewById(R.id.editTextStock);
        textViewMessageMAJEchantillon = findViewById(R.id.textViewMessageMAJEchantillon);

        findViewById(R.id.buttonAjouter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = editTextCode.getText().toString();
                String libelle = editTextLibelle.getText().toString();
                String stock = editTextStock.getText().toString();
                boolean intCode = false;
                //Log.i("logs","ajout du libele "+libelle+" dans la base");
                if (code.isEmpty() || libelle.isEmpty() || stock.isEmpty())
                {
                    textViewMessageMAJEchantillon.setText("Toutes les informations ne sont pas renseigner !");
                }
                else
                {
                    try {
                        database.open();
                        int stockInt = Integer.parseInt(stock);
                        if (database.getEchantillonWithCode(code) != null){
                            textViewMessageMAJEchantillon.setText("le Code existe déja dans la base de données !");
                        }
                        else {
                            Echantillon unEchantillon = new Echantillon(code,libelle,stock);
                            database.insererEchantillon(unEchantillon);
                            Cursor unCurseur = database.getDataEchantillons();
                            textViewMessageMAJEchantillon.setText("L'echantillon a été ajouter avec succès");
                            System.out.println("ajout d'un echantillon il y a maintenant :  "+String.valueOf(unCurseur.getCount())+" echantillons dans la BD");

                        }
                        database.close();
                    }
                    catch (NumberFormatException e){
                        textViewMessageMAJEchantillon.setText("le Stock doit etres un entier");
                    }
                }




            }
        });
    }
}
