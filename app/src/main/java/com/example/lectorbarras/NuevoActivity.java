package com.example.lectorbarras;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lectorbarras.db.Modelo;
import com.google.android.material.textfield.TextInputLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class NuevoActivity extends AppCompatActivity {
    TextInputLayout textInputLayout;
    EditText IdenMB, TypeMB, DescriptionMB, LocationMB;
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        textInputLayout = findViewById(R.id.CameraAction);
        IdenMB = findViewById(R.id.IdenMB);
        TypeMB = findViewById(R.id.TypeMB);
        DescriptionMB = findViewById(R.id.DescriptionMB);
        LocationMB = findViewById(R.id.LocationMB);
        btnInsert = findViewById(R.id.btnInsert);

        textInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(NuevoActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Lector - MB");
                integrator.setBeepEnabled(true);
                integrator.setOrientationLocked(true);
                integrator.setCaptureActivity(Capture.class);
                integrator.initiateScan();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!IdenMB.getText().toString().equals("") && !TypeMB.getText().toString().equals("") && !DescriptionMB.getText().toString().equals("") && !LocationMB.getText().toString().equals("")){
                 Modelo modelodb = new Modelo(NuevoActivity.this);
                 long id = modelodb.insertarMedio(IdenMB.getText().toString(), TypeMB.getText().toString(), DescriptionMB.getText().toString(), LocationMB.getText().toString());
                 if (id > 0) {
                     Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                     Limpiar();
                 } else {
                     Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                 }
                } else {
                    Toast.makeText(NuevoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void Limpiar(){
        IdenMB.setText("");
        TypeMB.setText("");
        DescriptionMB.setText("");
        LocationMB.setText("");
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Lectura Cancelada", Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                IdenMB.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
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