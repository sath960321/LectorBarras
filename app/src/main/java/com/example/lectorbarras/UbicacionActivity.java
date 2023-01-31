package com.example.lectorbarras;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.lectorbarras.db.Modelo;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class UbicacionActivity extends AppCompatActivity {
    TextInputLayout inputLayout;
    TextInputEditText searchIdenMB, AddIdenMB;
    TextView ShowLocation, ShowLocationDescription, ShowIdenMB;
    Button SearchActionBtn, AddActionBtn;
    CardView verCardView;
    ListView list1;
    private ArrayList telefonos;
    private ArrayAdapter adapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);
        inputLayout = findViewById(R.id.SearchAction);
        searchIdenMB = findViewById(R.id.searchIdenMB);
        ShowLocation = findViewById(R.id.ShowLocation);
        ShowLocationDescription = findViewById(R.id.ShowLocationDescription);
        ShowIdenMB = findViewById(R.id.ShowIdenMB);
        SearchActionBtn = findViewById(R.id.SearchActionBtn);
        verCardView = findViewById(R.id.CardViewVisibility);
        verCardView.setVisibility(View.INVISIBLE);

        AddIdenMB = findViewById(R.id.AddIdenMB);
        AddActionBtn = findViewById(R.id.AddActionBtn);
        list1 = findViewById(R.id.list1);
        telefonos = new ArrayList();
        telefonos.add("marcos : 43734843");
        telefonos.add("luis : 6554343");
        telefonos.add("ana : 7445434");
        adapter1=new ArrayAdapter(this,android.R.layout.simple_list_item_1,telefonos);
        list1.setAdapter(adapter1);

        AddActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!AddIdenMB.getText().toString().equals("")){
                    telefonos.add(AddIdenMB.getText().toString());
                }
            }
        });

        inputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanNow();
            }
        });
        SearchActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!searchIdenMB.getText().toString().equals("")){
                    Modelo modeldb = new Modelo(UbicacionActivity.this);
                    String carga = modeldb.BuscarMediosIdentificador(searchIdenMB.getText().toString());
                    verCardView.setVisibility(View.VISIBLE);
                    ShowIdenMB.setText(searchIdenMB.getText().toString());
                    ShowLocation.setText(carga);
                    ShowLocationDescription.setText(modeldb.devolverDepartamentoDescription(carga));
                } else {
                    Toast.makeText(UbicacionActivity.this, "Campo Vacío", Toast.LENGTH_LONG).show();
                }
                searchIdenMB.setText("");
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Lectura Cancelada", Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                searchIdenMB.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    protected void ScanNow(){
        IntentIntegrator integrator = new IntentIntegrator(UbicacionActivity.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Lector - MB");
        integrator.setBeepEnabled(true);
        integrator.setOrientationLocked(true);
        integrator.setCaptureActivity(Capture.class);
        integrator.initiateScan();
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