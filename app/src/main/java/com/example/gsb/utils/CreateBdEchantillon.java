package com.example.gsb.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.gsb.utils.BdAdapter.NOM_BDD;
import static com.example.gsb.utils.BdAdapter.VERSION_BDD;

public class CreateBdEchantillon extends SQLiteOpenHelper {

    private static final String TABLE_ECHANT = "echantillons";
    static final String COL_ID = "_id";
    private static final String COL_CODE = "CODE";
    private static final String COL_LIB = "LIB";
    private static final String COL_STOCK = "STOCK";

    private static final String TABLE_MOVEMENT = "mouvements";
    private static final String COL_DATE = "DATE_MOVE";
    private static final String COL_LIB_MOVE = "LIB";
    private static final String COL_UPDATE = "UPDATE_MOVE";



    private static final String CREATE_TABLE_ECHANTILLON = "CREATE TABLE " + TABLE_ECHANT + " ("+
            COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_CODE + " TEXT NOT NULL, " +
            COL_LIB + " TEXT NOT NULL, " +
            COL_STOCK + " TEXT NOT NULL);";

    private static final String CREATE_TABLE_MOVEMENT  = "CREATE TABLE " + TABLE_MOVEMENT + " (" +
            COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_CODE + " TEXT NOT NULL, " +
            COL_DATE + " TEXT NOT NULL, " +
            COL_LIB_MOVE + " INTEGER NOT NULL, " +
            COL_UPDATE + " INTEGER NOT NULL);";



    public CreateBdEchantillon(Context context) {
        super(context, NOM_BDD, null, VERSION_BDD);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_ECHANTILLON);
        db.execSQL(CREATE_TABLE_MOVEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ECHANT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVEMENT);
        onCreate(db);
    }

}
