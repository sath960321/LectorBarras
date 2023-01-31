package com.example.lectorbarras;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class GenerarCodigoActivity extends AppCompatActivity {
    TextInputLayout CodeText;
    TextInputEditText txtCodeText;
    Button GenerateCode;
    ImageView image_view;
    TextView viewDataBarcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_codigo);

        CodeText = findViewById(R.id.CodeText);
        txtCodeText = findViewById(R.id.txtCodeText);
        GenerateCode = findViewById(R.id.GenerateCode);
        image_view = findViewById(R.id.image_view);
        viewDataBarcode = findViewById(R.id.viewDataBarcode);

        GenerateCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = txtCodeText.getText().toString();
                viewDataBarcode.setText(content);
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.CODE_128, image_view.getWidth(), image_view.getHeight());
                    Bitmap bitmap = Bitmap.createBitmap(image_view.getWidth(), image_view.getHeight(), Bitmap.Config.RGB_565);
                    for (int i = 0; i<image_view.getWidth(); i++){
                        for (int j = 0; j<image_view.getHeight(); j++){
                            bitmap.setPixel(i, j, bitMatrix.get(i, j)? Color.BLACK:Color.WHITE);
                        }
                    }
                    image_view.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
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