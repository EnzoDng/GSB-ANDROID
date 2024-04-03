package com.example.gsb.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.gsb.Echantillon;
import com.example.gsb.Mouvement;

public class BdAdapter {
    static final int VERSION_BDD = 1;
    static final String NOM_BDD = "gsb.db";
    private static final String TABLE_ECHANT = "echantillons";
    private static final String TABLE_MOVEMENT = "mouvements";

    static final String COL_ID = "_id";
    static final int NUM_COL_ID = 0;

    // Columns for echantillons table
    public static final String COL_CODE_ECHANT = "CODE";
    static final int NUM_COL_CODE_ECHANT = 1;
    public static final String COL_LIB = "LIB";
    static final int NUM_COL_LIB = 2;
    public static final String COL_STOCK = "STOCK";
    static final int NUM_COL_STOCK = 3;

    // Columns for movements table
    public static final String COL_CODE_MOVE = "CODE";
    static final int NUM_COL_CODE_MOVE = 1;
    public static final String COL_DATE = "DATE_MOVE";
    static final int NUM_COL_DATE = 2;
    public static final String COL_LIB_MOVE = "LIB";
    static final int NUM_COL_LIB_MOVE = 3;
    public static final String COL_UPDATE = "UPDATE_MOVE";
    static final int NUM_COL_UPDATE = 4;

    private CreateBdEchantillon dbHelper;
    private Context context;
    private SQLiteDatabase db;
    private SQLiteDatabase dbMovements;

    public BdAdapter(Context context){

        dbHelper = new CreateBdEchantillon(context);
    }

    public BdAdapter open(){
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public BdAdapter close (){
        dbHelper.close();
        return this;
    }

    // Echantillons operations
    public long insererEchantillon(Echantillon unEchant){
        ContentValues values = new ContentValues();
        values.put(COL_CODE_ECHANT, unEchant.getCode());
        values.put(COL_LIB, unEchant.getLibelle());
        values.put(COL_STOCK, unEchant.getQuantiteStock());
        return db.insert(TABLE_ECHANT, null, values);
    }

    private Echantillon cursorToEchant(Cursor c){
        Echantillon unEchant=null ;
        if (c != null && c.moveToFirst()){
            unEchant = new Echantillon();
            unEchant.setCode(c.getString(NUM_COL_CODE_ECHANT));
            unEchant.setLibelle(c.getString(NUM_COL_LIB));
            unEchant.setQuantiteStock(c.getString(NUM_COL_STOCK));
        }
        if (c != null && !c.isClosed()) {
            c.close();
        }
        return unEchant;
    }

    public Echantillon getEchantillonWithCode(String unCode){
        Cursor c = db.query(TABLE_ECHANT, new String[] {COL_ID, COL_CODE_ECHANT, COL_LIB, COL_STOCK}, COL_CODE_ECHANT + " LIKE ?", new String[]{unCode}, null, null, null);
        return cursorToEchant(c);
    }

    public int updateEchantillon(String unCode, Echantillon unEchant){
        ContentValues values = new ContentValues();
        values.put(COL_CODE_ECHANT, unEchant.getCode());
        values.put(COL_LIB, unEchant.getLibelle());
        values.put(COL_STOCK, unEchant.getQuantiteStock());
        return db.update(TABLE_ECHANT, values, COL_CODE_ECHANT + " = ?", new String[]{unCode});
    }

    public int removeEchantillonWithCode(String unCode){
        return db.delete(TABLE_ECHANT, COL_CODE_ECHANT + " = ?", new String[]{unCode});
    }

    public Cursor getDataEchantillons(){
        return db.rawQuery("SELECT * FROM " + TABLE_ECHANT, null);
    }

    // Opérations sur les mouvements
    public long insertMovement(Mouvement mouvement) {
        ContentValues values = new ContentValues();
        values.put(COL_CODE_MOVE, mouvement.getCode());
        values.put(COL_DATE, mouvement.getDate());
        values.put(COL_LIB_MOVE, mouvement.getLib());
        values.put(COL_UPDATE, mouvement.getUpdate());
        return db.insert(TABLE_MOVEMENT, null, values);
    }

    private Mouvement cursorToMouvement(Cursor c) {
        Mouvement mouvement = null;
        if (c != null && c.moveToFirst()) {
            mouvement = new Mouvement();
            mouvement.setCode(c.getString(NUM_COL_CODE_MOVE));
            mouvement.setDate(c.getString(NUM_COL_DATE));
            mouvement.setLib(c.getString(NUM_COL_LIB_MOVE));
            mouvement.setUpdate(c.getInt(NUM_COL_UPDATE));
        }
        if (c != null && !c.isClosed()) {
            c.close();
        }
        return mouvement;
    }

    public Mouvement getMouvementWithCode(String code) {
        Cursor c = db.query(TABLE_MOVEMENT, new String[] {COL_ID,COL_CODE_MOVE,COL_DATE,COL_LIB_MOVE,COL_UPDATE}, COL_CODE_MOVE + " LIKE ?", new String[]{code}, null, null, null);
        return cursorToMouvement(c);
    }

    public Cursor getDataMouvement(String code){
        String query = "SELECT * FROM " + TABLE_MOVEMENT + " WHERE " + COL_CODE_MOVE + " LIKE ?";
        String[] selectionArgs = { "%" + code + "%" }; // %code% pour trouver les correspondances partielles
        return db.rawQuery(query, selectionArgs);
    }
}
