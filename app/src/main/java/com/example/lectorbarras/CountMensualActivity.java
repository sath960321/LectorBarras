package com.example.lectorbarras;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lectorbarras.adapters.ListaMediosContados;
import com.example.lectorbarras.clases.ConteoMensual;
import com.example.lectorbarras.clases.Medios;
import com.example.lectorbarras.db.Modelo;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Calendar;

public class CountMensualActivity extends AppCompatActivity {
    TextInputLayout CalendarAction, CameraActionCM;
    TextInputEditText fechaCM, idenMBCM, TypeMB, DescriptionMB, LocationMB;
    Button btnGuardar;
    RecyclerView listaMediosContados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countmensual);
        CalendarAction = findViewById(R.id.CalendarAction);
        CameraActionCM = findViewById(R.id.CameraActionCM);
        fechaCM = findViewById(R.id.FechaCM);
        idenMBCM = findViewById(R.id.IdenMBCM);
        TypeMB = findViewById(R.id.TypeMB);
        DescriptionMB = findViewById(R.id.DescriptionMB);
        LocationMB = findViewById(R.id.LocationMB);
        TypeMB.setInputType(InputType.TYPE_NULL);
        DescriptionMB.setInputType(InputType.TYPE_NULL);
        LocationMB.setInputType(InputType.TYPE_NULL);
        btnGuardar = findViewById(R.id.btnGuardar);
        listaMediosContados = findViewById(R.id.listaMediosContados);


        Calendar calendar = Calendar.getInstance();
        final  int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        CalendarAction.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CountMensualActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        fechaCM.setText(date);
                        fechaCM.setInputType(InputType.TYPE_NULL);
                    }
                }, year,month,day);
                datePickerDialog.show();
            }
        });

        CameraActionCM.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ScanNow();
               if (!idenMBCM.getText().toString().equals("")){
                   Modelo modeldb = new Modelo(CountMensualActivity.this);
                   Medios ObtenerMedios = new Medios();
                   ObtenerMedios = modeldb.obtenerMediosCM(idenMBCM.getText().toString());
                   TypeMB.setText(ObtenerMedios.getType());
               }
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fechaCM.getText().toString().equals("") && !idenMBCM.getText().toString().equals("") && !TypeMB.getText().toString().equals("") && !DescriptionMB.getText().toString().equals("") && !LocationMB.getText().toString().equals("")){
                    Modelo modeldb = new Modelo(CountMensualActivity.this);
                    ConteoMensual Exist = modeldb.ElementoExistente(fechaCM.getText().toString(), idenMBCM.getText().toString());
                    if (Exist == null){
                        long id = modeldb.insertarMedioCM(fechaCM.getText().toString(), idenMBCM.getText().toString(), TypeMB.getText().toString(), DescriptionMB.getText().toString(), LocationMB.getText().toString());
                        if (id > 0) {
                            Toast.makeText(CountMensualActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                            Limpiar();
                        } else {
                            Toast.makeText(CountMensualActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(CountMensualActivity.this, "ESE REGISTRO EXISTE", Toast.LENGTH_LONG).show();
                        Limpiar();
                    }
                } else {
                    Toast.makeText(CountMensualActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
         listaMediosContados.setLayoutManager(new LinearLayoutManager(this));
         Modelo modeldb = new Modelo(CountMensualActivity.this);
        ListaMediosContados adapter = new ListaMediosContados(CountMensualActivity.this, R.layout.activity_lista_contados, modeldb.mostrarMediosInv());
        listaMediosContados.setAdapter(adapter);

    }

    private void Limpiar(){
        idenMBCM.setText("");
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
                idenMBCM.setText(result.getContents());
                Modelo modeldb = new Modelo(CountMensualActivity.this);
                Medios ObtenerMedios = new Medios();
                ObtenerMedios = modeldb.obtenerMediosCM(idenMBCM.getText().toString());
                TypeMB.setText(ObtenerMedios.getType());
                DescriptionMB.setText(ObtenerMedios.getDescription());
                LocationMB.setText(ObtenerMedios.getLocation());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
    protected void ScanNow(){
        IntentIntegrator integrator = new IntentIntegrator(CountMensualActivity.this);
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