package com.example.gsb;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gsb.utils.BdAdapter;

public class majEchantillon extends AppCompatActivity {
    private EditText editTextCode;
    private EditText editTextStock;
    private TextView textViewMessageMAJEchantillon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maj_echantillon);
        BdAdapter database = new BdAdapter(this);
        editTextCode = findViewById(R.id.editTextCode);
        editTextStock = findViewById(R.id.editTextStock);
        textViewMessageMAJEchantillon = findViewById(R.id.textViewMessageMAJEchantillon);



        // Assurez-vous d'avoir un bouton dans votre layout XML avec l'ID "buttonQuitterListe"
        findViewById(R.id.buttonQuitterListe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Terminer cette activité et retourner à l'activité précédente (MainActivity)
                finish();
            }
        });

        findViewById(R.id.buttonAjouter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = editTextCode.getText().toString();
                String stock = editTextStock.getText().toString();

                if (code.isEmpty() || stock.isEmpty())
                {
                    textViewMessageMAJEchantillon.setText("Toutes les informations ne sont pas renseigner !");
                }
                else{
                    try {
                        int stockInt = Integer.parseInt(editTextStock.getText().toString());
                        database.open();
                        if (database.getEchantillonWithCode(code) == null){
                            textViewMessageMAJEchantillon.setText("Code inexistant dans la base de données !");
                        }
                        else{
                            Echantillon echantillon = database.getEchantillonWithCode(code);
                            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
                            Mouvement unMouvement = new Mouvement(echantillon.getCode(), sqlDate.toString(), "Ajout de : ", stockInt);
                            long insertedRowId = database.insertMovement(unMouvement);
                            textViewMessageMAJEchantillon.setText(String.valueOf(insertedRowId));
                            int nouveau_stock = Integer.parseInt(echantillon.getQuantiteStock())+stockInt;
                            echantillon.setQuantiteStock(String.valueOf(nouveau_stock));
                            textViewMessageMAJEchantillon.setText(stock + " unités de stock ont été supprimées. Le nouveau stock est de " + echantillon.getQuantiteStock());
                            database.updateEchantillon(code,echantillon);
                        }
                        database.close();
                    }
                    catch (NumberFormatException e){
                        textViewMessageMAJEchantillon.setText("le Stock doit etre un entier");
                    }
                }
            }
        });

        findViewById(R.id.buttonSupprimer).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String code = editTextCode.getText().toString();
                String stock = editTextStock.getText().toString();

                if (code.isEmpty() || stock.isEmpty())
                {
                    textViewMessageMAJEchantillon.setText("Toutes les informations ne sont pas renseigner !");
                }
                else {
                    try {
                        database.open();
                        int stockInt = Integer.parseInt(editTextStock.getText().toString());
                        if (database.getEchantillonWithCode(code) == null){
                            textViewMessageMAJEchantillon.setText("Code inexistant dans la base de données !");
                        }
                        else{
                            Echantillon echantillon = database.getEchantillonWithCode(code);
                            int quantiteStock = Integer.parseInt(echantillon.getQuantiteStock());
                            if (quantiteStock < stockInt) {
                                textViewMessageMAJEchantillon.setText("Quantité insuffisante en stock");
                            } else {
                                int nouveau_stock = quantiteStock - stockInt;
                                echantillon.setQuantiteStock(String.valueOf(nouveau_stock));
                                java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
                                Mouvement unMouvement = new Mouvement(echantillon.getCode(), sqlDate.toString(), "Suppression de : ", stockInt);
                                long insertedRowId = database.insertMovement(unMouvement);
                                textViewMessageMAJEchantillon.setText(stock + " unités de stock ont été supprimées. Le nouveau stock est de " + echantillon.getQuantiteStock());
                                database.updateEchantillon(code, echantillon);
                                database.close();
                            }
                        }
                    }
                    catch (NumberFormatException e){
                        textViewMessageMAJEchantillon.setText("le Stock doit etre un entier");
                    }
                }
            }
        });

    }
}
