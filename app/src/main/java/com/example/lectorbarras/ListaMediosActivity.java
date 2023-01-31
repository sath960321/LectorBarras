package com.example.lectorbarras;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lectorbarras.adapters.ListaMediosAdapter;
import com.example.lectorbarras.db.Modelo;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ListaMediosActivity extends AppCompatActivity {
    RecyclerView listaMedios;
    TextInputLayout searchIdAction;
    TextInputEditText searchIdenMB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medios);
        listaMedios = findViewById(R.id.listaMedios);
        searchIdAction = findViewById(R.id.SearchIdAction);
        searchIdenMB = findViewById(R.id.searchIdenMB);

        listaMedios.setLayoutManager(new LinearLayoutManager(this));
        Modelo modeldb = new Modelo(ListaMediosActivity.this);
        ListaMediosAdapter adapter = new ListaMediosAdapter(ListaMediosActivity.this, R.layout.activity_lista_item_medios, modeldb.mostrarMedios());
        listaMedios.setAdapter(adapter);
        searchIdAction.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getFilter().filter(searchIdenMB.getText().toString());
                searchIdenMB.setText("");
            }
        });
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