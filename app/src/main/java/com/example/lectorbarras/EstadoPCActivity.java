package com.example.lectorbarras;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

public class EstadoPCActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompletetextview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_pcactivity);
        autoCompletetextview2 = (AutoCompleteTextView) findViewById(R.id.autoCompletetextview2);

        String [] opciones = {"Buena", "Rota"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, opciones);
        autoCompletetextview2.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater;
        inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }
    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuListar:
                Intent intentListar = new Intent(this, ListaMediosActivity.class);
                startActivity(intentListar);
                return true;
            case R.id.menuUbicacion:
                Intent intentUbica = new Intent( this, UbicacionActivity.class);
                startActivity(intentUbica);
                return true;
            case R.id.menuNuevo:
                Intent intentNuevo = new Intent(this, NuevoActivity.class);
                startActivity(intentNuevo);
                return true;
            case R.id.menuInventario:
                Intent intentInvMen = new Intent(this, CountMensualActivity.class);
                startActivity(intentInvMen);
                return true;
            case R.id.menuFichaTecnica:
                Intent intentFichaTecnica = new Intent(this, FichaTecnica.class);
                startActivity(intentFichaTecnica);
                return true;
            case R.id.menuEstadoPC:
                Intent intentStatusPC = new Intent(this, EstadoPCActivity.class);
                startActivity(intentStatusPC);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
