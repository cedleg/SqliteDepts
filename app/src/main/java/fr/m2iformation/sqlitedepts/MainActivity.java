package fr.m2iformation.sqlitedepts;

import android.content.DialogInterface;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fr.m2iformation.sqlitedepts.bll.Departement;

public class MainActivity extends AppCompatActivity {

    private Departement departement;

    private EditText edSearch, edNoDept, edNoRegion, edNomDept, edNomStdDept, edAreaDept, edCreateDateDept, edCapitalDept, edUrlDept, txtNomRegion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edSearch = findViewById(R.id.txtSearch);
        edNoDept = findViewById(R.id.txtNoDept);
        edNoRegion = findViewById(R.id.txtNoRegion);
        edNomDept = findViewById(R.id.txtNom);
        edNomStdDept = findViewById(R.id.txtNomStd);
        edAreaDept = findViewById(R.id.txtSurface);
        edCreateDateDept = findViewById(R.id.txtDateCreation);
        edCapitalDept = findViewById(R.id.txtChefLieu);
        edUrlDept = findViewById(R.id.txtUrlWiki);
        txtNomRegion = findViewById(R.id.txtNomRegion);

        departement = new Departement(this);
    }

    public void btn_Search(View v){
        try {
            departement.select(edSearch.getText().toString());
            parametersFields(departement.getNoDept(), departement.getNoRegion(), departement.getNom(), departement.getNomStd(), departement.getSurface(), departement.getDateCreation(), departement.getChefLieu(), departement.getUrlWiki());
            Toast.makeText(MainActivity.this, "Search entry for " + edSearch.getText().toString(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        hideKeyboard();
    }

    public void btnInsert(View v){
        departement.setNoDept(edNoDept.getText().toString());

        if(isNumeric(edNoRegion.getText().toString())) {
            departement.setNoRegion(Integer.parseInt(edNoRegion.getText().toString()));
        }else{
            departement.setNoRegion(-1);
        }
        departement.setNom(edNomDept.getText().toString());
        departement.setNomStd(edNomStdDept.getText().toString());

        if(isNumeric(edAreaDept.getText().toString())) {
            departement.setSurface(Integer.parseInt(edAreaDept.getText().toString()));
        }else{
            departement.setSurface(-1);
        }
        departement.setDateCreation(edCreateDateDept.getText().toString());
        departement.setChefLieu(edCapitalDept.getText().toString());
        departement.setUrlWiki(edUrlDept.getText().toString());

        try {
            departement.insert();
            Toast.makeText(MainActivity.this, "Successful insert entry " + edNoDept.getText().toString(), Toast.LENGTH_LONG).show();
            hideKeyboard();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void btnDelete(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialogBuilder.setTitle("Confirmation");
        alertDialogBuilder.setMessage("Valider supression ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Oui",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int pos) {
                try {
                    departement.delete();
                    parametersFields("", -1, "", "", -1, "", "", "");
                    Toast.makeText(MainActivity.this, "Database deleted entry " + departement.getNom(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Failed deleted entry ", Toast.LENGTH_LONG).show();
                }
            }})

                .setNegativeButton("Non",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int pos) {
                        dialog.cancel();
                        Toast.makeText(MainActivity.this, "Cancel deleted entry ", Toast.LENGTH_LONG).show();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void btnSave(View v){
        departement.setNoDept(edNoDept.getText().toString());

        if(isNumeric(edNoRegion.getText().toString())) {
            departement.setNoRegion(Integer.parseInt(edNoRegion.getText().toString()));
        }else{
            departement.setNoRegion(-1);
        }
        departement.setNom(edNomDept.getText().toString());
        departement.setNomStd(edNomStdDept.getText().toString());

        if(isNumeric(edAreaDept.getText().toString())) {
            departement.setSurface(Integer.parseInt(edAreaDept.getText().toString()));
        }else{
            departement.setSurface(-1);
        }
        departement.setDateCreation(edCreateDateDept.getText().toString());
        departement.setChefLieu(edCapitalDept.getText().toString());
        departement.setUrlWiki(edUrlDept.getText().toString());
        try {
            departement.update();
            Toast.makeText(MainActivity.this, "Successful update entry " + edNoDept.getText().toString(), Toast.LENGTH_LONG).show();
            hideKeyboard();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void btnClear(View v){
        parametersFields("", -1, "", "", -1, "", "", "");
        txtNomRegion.setText("");
    }

    public void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if(imm != null && imm.isAcceptingText()){
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void parametersFields(String NoDept, int NoRegion, String NomDept, String NomStdDept, int AreaDept, String CreateDateDept, String CapitalDept, String UrlDept){
        edNoDept.setText(NoDept, TextView.BufferType.EDITABLE);
        if(NoRegion > 0) {
            edNoRegion.setText(String.valueOf(NoRegion), TextView.BufferType.EDITABLE);
        }else {
            edNoRegion.setText("", TextView.BufferType.EDITABLE);
        }

        txtNomRegion.setText(departement.getRegion().getNom());
        edNomDept.setText(NomDept, TextView.BufferType.EDITABLE);
        edNomStdDept.setText(NomStdDept, TextView.BufferType.EDITABLE);
        if(AreaDept > 0) {
            edAreaDept.setText(String.valueOf(AreaDept), TextView.BufferType.EDITABLE);
        } else {
            edAreaDept.setText("", TextView.BufferType.EDITABLE);
        }
        edCreateDateDept.setText(CreateDateDept, TextView.BufferType.EDITABLE);
        edCapitalDept.setText(CapitalDept, TextView.BufferType.EDITABLE);
        edUrlDept.setText(UrlDept, TextView.BufferType.EDITABLE);
    }

    public boolean isNumeric(String str){
        boolean isNum = false;
        try{
            Integer.parseInt(str);
            isNum = true;
        }catch (NumberFormatException e){
            isNum = false;
        }
        return isNum;
    }
}
