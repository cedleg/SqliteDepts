package fr.m2iformation.sqlitedepts.bll;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import fr.m2iformation.sqlitedepts.dal.DbInit;

public class Region {

    private SQLiteDatabase db;
    private DbInit dbInit;

    private int noRegion;
    private String nom;

    public Region(Context c){
        dbInit = DbInit.getInstance(c);
        db = dbInit.getWritableDatabase();
    }

    public Region(Context c, Integer no) throws Exception {
        this(c);
        select(no);
    }

    public int getNoRegion() {
        return noRegion;
    }

    public void setNoRegion(int noRegion) {
        this.noRegion = noRegion;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void select(Integer no) throws Exception {

        try {
            Cursor cursor = db.query("regions", new String[]{"no_region", "nom"},
                    "no_region = ?", new String[] {no.toString()}, null, null, null);
            if(cursor.moveToFirst()){
                this.noRegion = cursor.getInt(cursor.getColumnIndex("no_region"));
                this.nom = cursor.getString(cursor.getColumnIndex("nom"));
                cursor.close();
            } else{
                throw new DbException("Le departement " + this.nom + " n'existe pas!");
            }
        }catch (SQLException e){
            throw new DbException("Le departement " + this.nom + " n'existe pas!");
        }
    }
}
