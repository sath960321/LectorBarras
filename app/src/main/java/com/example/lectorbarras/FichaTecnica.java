package com.example.lectorbarras;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.lectorbarras.adapters.ListaMediosAdapter;
import com.example.lectorbarras.clases.Medios;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FichaTecnica extends AppCompatActivity {
    private TextInputLayout URLAction;
    private TextInputEditText txtURLServices;
    private RequestQueue rq;
    private RecyclerView listaItemNET;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_tecnica);
        URLAction = findViewById(R.id.URLAction);
        txtURLServices = findViewById(R.id.txtURLServices);
        rq = Volley.newRequestQueue(this);
        listaItemNET = findViewById(R.id.listaItemNET);
        listaItemNET.setLayoutManager(new LinearLayoutManager(this));
        loadingPB = findViewById(R.id.idLoadingPB);

        URLAction.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Recuperar();
            }
        });

    }

    public void Recuperar(){
        String url = "http://10.52.60.246:3000/modelo/listar.php";
        loadingPB.setVisibility(View.VISIBLE);
        JsonArrayRequest requerimiento = new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        loadingPB.setVisibility(View.GONE);
                        ArrayList<Medios> lista = new ArrayList<>();
                        for (int f=0;f<response.length();f++){
                            try {
                                JSONObject objeto = new JSONObject(response.get(f).toString());
                                Medios medios = new Medios();
                                medios.setId(objeto.getInt("id"));
                                medios.setIdenMB(objeto.getString("idenMB"));
                                medios.setType(objeto.getString("type"));
                                medios.setDescription(objeto.getString("description"));
                                medios.setLocation(objeto.getString("location"));
                                lista.add(medios);
                                ListaMediosAdapter adaptador = new ListaMediosAdapter(FichaTecnica.this, R.layout.activity_lista_item_medios, lista);
                                listaItemNET.setAdapter(adaptador);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof ServerError){
                            Log.i("TAG", "SERVER ERROR");
                            Toast.makeText(FichaTecnica.this, "SERVER ERROR", Toast.LENGTH_LONG).show();
                        }
                        if (error instanceof NoConnectionError){
                            Log.i("TAG", "No Internet Connection");
                            Toast.makeText(FichaTecnica.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        rq.add(requerimiento);
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