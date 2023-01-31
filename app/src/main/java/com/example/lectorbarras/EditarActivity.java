package com.example.lectorbarras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lectorbarras.clases.Medios;
import com.example.lectorbarras.db.Modelo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class EditarActivity extends AppCompatActivity {
    TextInputEditText txtIdenMB, txtTypeMB, txtDescriptionMB, txtLocation;
    FloatingActionButton fabEdit, fabDelete;
    Button btnSave;
    boolean correcto = false;
    Medios medios;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtIdenMB = findViewById(R.id.IdenMB);
        txtTypeMB = findViewById(R.id.TypeMB);
        txtDescriptionMB = findViewById(R.id.DescriptionMB);
        txtLocation = findViewById(R.id.LocationMB);
        btnSave = findViewById(R.id.btnSave);
        fabEdit = findViewById(R.id.fabEdit);
        fabDelete = findViewById(R.id.fabDelete);
        fabEdit.setVisibility(View.INVISIBLE);
        fabDelete.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final Modelo modeldb = new Modelo(EditarActivity.this);
        medios = modeldb.verMedios(id);

        if (medios != null){
            txtIdenMB.setText(medios.getIdenMB());
            txtTypeMB.setText(medios.getType());
            txtDescriptionMB.setText(medios.getDescription());
            txtLocation.setText(medios.getLocation());
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtIdenMB.getText().toString().equals("") && !txtTypeMB.getText().toString().equals("") && !txtDescriptionMB.getText().toString().equals("") && !txtLocation.getText().toString().equals("")){
                    correcto = modeldb.editarMedio(id, txtIdenMB.getText().toString(), txtTypeMB.getText().toString(), txtDescriptionMB.getText().toString(),txtLocation.getText().toString());
                        if(correcto){
                            Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                            verRegistro();
                        } else {
                            Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                        }
                } else {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}