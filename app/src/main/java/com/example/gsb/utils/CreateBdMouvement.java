package com.example.gsb.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CreateBdMouvement extends SQLiteOpenHelper {

    private static final String TABLE_MOVEMENT = "mouvements";
    static final String COL_ID = "_id";
    private static final String COL_CODE = "CODE";
    private static final String COL_DATE = "DATE_MOVE";
    private static final String COL_LIB_MOVE = "LIB";
    private static final String COL_UPDATE = "UPDATE_MOVE";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_MOVEMENT + " (" +
            COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_CODE + " TEXT NOT NULL, " +
            COL_DATE + " TEXT NOT NULL, " +
            COL_LIB_MOVE + " TEXT NOT NULL, " +
            COL_UPDATE + " INTEGER NOT NULL);";


    public CreateBdMouvement(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // appelée lorsqu’aucune base n’existe et que la classe utilitaire doit en créer une
        //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
        Log.i("sqlDb",CREATE_BDD);
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade logic here if needed
    }
}
