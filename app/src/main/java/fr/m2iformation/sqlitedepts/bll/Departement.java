package fr.m2iformation.sqlitedepts.bll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.regex.Pattern;

import fr.m2iformation.sqlitedepts.dal.DbInit;

public class Departement {

    private SQLiteDatabase db;
    private DbInit dbInit;

    private String noSearch;

    private String noDept;
    private int noRegion;
    private String nom;
    private String nomStd;
    private int surface;
    private String dateCreation;
    private String chefLieu;
    private String urlWiki;
    private Region region;

    private Context ctxt;


    public Departement(Context c){
        dbInit = DbInit.getInstance(c);
        db = dbInit.getWritableDatabase();
        this.ctxt = c;
    }

    public Departement(Context c, String no){
        dbInit = DbInit.getInstance(c);
        db = dbInit.getWritableDatabase();
        this.noSearch = no;
        this.ctxt = c;
    }

    public void insert() throws Exception {

        validateData();

        ContentValues values = new ContentValues();
        values.put("no_dept", this.noDept);
        values.put("no_region", this.noRegion);
        values.put("nom", this.nom);
        values.put("nom_std", this.nomStd);
        values.put("surface", this.surface);
        values.put("date_creation", this.dateCreation);
        values.put("chef_lieu", this.chefLieu);
        values.put("url_wiki", this.urlWiki);

        try {
            db.insert("departements", null, values);

        }catch (SQLException e){
            throw new DbException("Insert database error");
        }
    }

    public void select(String no) throws Exception {

        try {
            Cursor cursor = db.query("departements", new String[]{"no_dept", "no_region", "nom", "nom_std", "surface", "date_creation", "chef_lieu", "url_wiki"},
                    "no_dept = ?", new String[] {no}, null, null, null);
            if(cursor.moveToFirst()){
                this.noDept = cursor.getString(cursor.getColumnIndex("no_dept"));
                this.noRegion = cursor.getInt(cursor.getColumnIndex("no_region"));
                this.nom = cursor.getString(cursor.getColumnIndex("nom"));
                this.nomStd = cursor.getString(cursor.getColumnIndex("nom_std"));
                this.surface = cursor.getInt(cursor.getColumnIndex("surface"));
                this.dateCreation = cursor.getString(cursor.getColumnIndex("date_creation"));
                this.chefLieu = cursor.getString(cursor.getColumnIndex("chef_lieu"));
                this.urlWiki = cursor.getString(cursor.getColumnIndex("url_wiki"));
                region = new Region(this.ctxt, noRegion);
            cursor.close();
            } else{
                throw new DbException("Le departement " + this.noDept + " n'existe pas!");
            }
        }catch (SQLException e){
            throw new DbException("Le departement " + this.noDept + " n'existe pas!");
        }
    }

    public void update() throws Exception {

        validateData();

        ContentValues values = new ContentValues();
        values.put("no_dept", this.noDept);
        values.put("no_region", this.noRegion);
        values.put("nom", this.nom);
        values.put("nom_std", this.nomStd);
        values.put("surface", this.surface);
        values.put("date_creation", this.dateCreation);
        values.put("chef_lieu", this.chefLieu);
        values.put("url_wiki", this.urlWiki);

        try{
            db.update("departements", values, "no_dept = ?", new String[] {this.noDept});
        }catch (SQLException e){
            throw new DbException("Le departement " + this.noDept + " ne peut pas etre mis a jour");
        }

    }

    public void delete () throws Exception {

        try {
            db.delete("departements", "nom = ?",new String[] {this.nom});
        }catch (SQLException e){
            throw new DbException("Le departement " + this.noDept + " ne peux etre supprimé");
         }
    }

    public void  validateData() throws DbException {
        if(this.noDept.isEmpty()) throw new DbException("Department number empty!");
        if(Pattern.matches("[0-9]{2}|2A|2B|97[0-5]", this.noDept)) throw new DbException("Department number incorrect pattern!");
        if(this.nom.isEmpty()) throw new DbException("Department name empty!");
    }

    public String getNoDept() {
        return noDept;
    }

    public void setNoDept(String noDept) {
        this.noDept = noDept;
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

    public String getNomStd() {
        return nomStd;
    }

    public void setNomStd(String nomStd) {
        this.nomStd = nomStd;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getChefLieu() {
        return chefLieu;
    }

    public void setChefLieu(String chefLieu) {
        this.chefLieu = chefLieu;
    }

    public String getUrlWiki() {
        return urlWiki;
    }

    public void setUrlWiki(String urlWiki) {
        this.urlWiki = urlWiki;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
