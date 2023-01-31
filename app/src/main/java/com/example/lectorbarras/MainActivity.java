package com.example.lectorbarras;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {
    MaterialCardView ListadoMedios, Inventory, NewItem, FichaTecnica, EstadoPC, GenerateCodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListadoMedios = findViewById(R.id.ListadoMedios);
        Inventory = findViewById(R.id.Inventory);
        NewItem = findViewById(R.id.NewItem);
        FichaTecnica = findViewById(R.id.FichaTecnica);
        EstadoPC = findViewById(R.id.EstadoPC);
        GenerateCodes = findViewById(R.id.GenerateCodes);


        ListadoMedios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentListar = new Intent(MainActivity.this, ListaMediosActivity.class);
                startActivity(intentListar);
            }
        });
        Inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intentInventory = new Intent(MainActivity.this, CountMensualActivity.class);
               startActivity(intentInventory);
            }
        });
        NewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNew = new Intent(MainActivity.this, NuevoActivity.class);
                startActivity(intentNew);
            }
        });
        FichaTecnica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentFicha = new Intent(MainActivity.this, FichaTecnica.class);
                startActivity(intentFicha);
            }
        });
        EstadoPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEstado = new Intent(MainActivity.this, EstadoPCActivity.class);
                startActivity(intentEstado);
            }
        });
        GenerateCodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGenerarCodes = new Intent(MainActivity.this, GenerarCodigoActivity.class);
                startActivity(intentGenerarCodes);
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