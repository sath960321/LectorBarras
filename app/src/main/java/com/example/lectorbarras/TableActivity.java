package com.example.lectorbarras;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lectorbarras.clases.TableDynamic;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {
    TableLayout table;
    EditText txtName, txtLastName;
    private String[]header = {"Nombre","Apellidos"};
    private ArrayList<String[]> rows = new ArrayList<>();
    private TableDynamic tableDynamic;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        table = (TableLayout)findViewById(R.id.table);
        txtName = (EditText)findViewById(R.id.txtName);
        txtLastName = (EditText)findViewById(R.id.txtLastName);
        button2 = findViewById(R.id.button2);
        tableDynamic = new TableDynamic(table, getApplicationContext());
        tableDynamic.addHeader(header);
        tableDynamic.addData(getClients());

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] item = new String[]{txtName.getText().toString(),txtLastName.getText().toString()};
                tableDynamic.addItems(item);
            }
        });
    }
    private ArrayList<String[]> getClients(){
        rows.add(new String[]{"Pedro","Lopez"});
        rows.add(new String[]{"Sofia","Alfaro"});
        rows.add(new String[]{"Naomi","Espejel"});
        rows.add(new String[]{"Lorena","Gallegos"});
        return rows;
    }
}