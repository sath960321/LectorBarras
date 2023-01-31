package com.example.lectorbarras.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.lectorbarras.clases.ConteoMensual;
import com.example.lectorbarras.clases.Departamento;
import com.example.lectorbarras.clases.Medios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Modelo extends ConexionSQLite{
    Context context;

    public Modelo(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarMedio(String IdenMB, String type, String description, String location) {
        long id = 0;
        try {
            ConexionSQLite conex = new ConexionSQLite(context);
            SQLiteDatabase db = conex.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("idenMB", IdenMB);
            values.put("type", type);
            values.put("description", description);
            values.put("location", location);
            id = db.insert(TABLE_MEDIOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
}

    public ArrayList<Medios> mostrarMedios() {
        ConexionSQLite conex = new ConexionSQLite(context);
        SQLiteDatabase db = conex.getWritableDatabase();
        ArrayList<Medios> listaMedios = new ArrayList<>();
        Medios medios;
        Cursor cursorMedios;
        cursorMedios = db.rawQuery("SELECT * FROM " + TABLE_MEDIOS, null);
        if (cursorMedios.moveToFirst()) {
            do {
                medios = new Medios();
                medios.setId(cursorMedios.getInt(0));
                medios.setIdenMB(cursorMedios.getString(1));
                medios.setType(cursorMedios.getString(2));
                medios.setDescription(cursorMedios.getString(3));
                medios.setLocation(cursorMedios.getString(4));
                listaMedios.add(medios);
            } while (cursorMedios.moveToNext());
        }
        cursorMedios.close();
        return listaMedios;
    }


    public Medios verMedios(int id){
        ConexionSQLite conex = new ConexionSQLite(context);
        SQLiteDatabase db =conex.getWritableDatabase();
        Medios medios = null;
        Cursor cursorMedios;
        cursorMedios = db.rawQuery("SELECT id, rtrim(idenMB), type, description, location FROM "+ TABLE_MEDIOS + " WHERE id = "+ id + " LIMIT 5", null);
        if (cursorMedios.moveToFirst()){
            medios = new Medios();
            medios.setId(cursorMedios.getInt(0));
            medios.setIdenMB(cursorMedios.getString(1));
            medios.setType(cursorMedios.getString(2));
            medios.setDescription(cursorMedios.getString(3));
            medios.setLocation(cursorMedios.getString(4));
        }
        cursorMedios.close();
        return medios;
    }
    
    public String BuscarMediosIdentificador(String IdenMB){
        ConexionSQLite conex = new ConexionSQLite(context);
        SQLiteDatabase db =conex.getWritableDatabase();
        Medios medios = null;
        Cursor cursorMedios;
        cursorMedios = db.rawQuery("SELECT * FROM "+ TABLE_MEDIOS + " WHERE rtrim(idenMB) = '"+ IdenMB +"'", null);
        if (cursorMedios.moveToFirst()) {
            do {
                medios = new Medios();
                medios.setId(cursorMedios.getInt(0));
                medios.setIdenMB(cursorMedios.getString(1));
                medios.setType(cursorMedios.getString(2));
                medios.setDescription(cursorMedios.getString(3));
                medios.setLocation(cursorMedios.getString(4));
            } while (cursorMedios.moveToNext());
        }
        cursorMedios.close();
        if (medios != null){
            return medios.getLocation();
        } else {
            return "Elemento No encontrado";
        }
    }

    public String devolverDepartamentoDescription(String data){
        ConexionSQLite conex = new ConexionSQLite(context);
        SQLiteDatabase db =conex.getWritableDatabase();
        Departamento departamento = null;
        Cursor cursorDepartamento;
        cursorDepartamento = db.rawQuery("SELECT * FROM "+ TABLE_DEPARTAMENTO + " WHERE rtrim(location) = '"+ data.trim() +"'", null);
        if (cursorDepartamento.moveToFirst()){
            do {
                departamento = new Departamento();
                departamento.setId(cursorDepartamento.getInt(0));
                departamento.setLocation(cursorDepartamento.getString(1));
                departamento.setDescription(cursorDepartamento.getString(2));
            } while (cursorDepartamento.moveToNext());
        }
        cursorDepartamento.close();
        if (departamento != null){
            return departamento.getDescription();
        } else {
            return "Elemento No Encontrado";
        }
    }

    
    public boolean editarMedio(int id, String IdenMB, String type, String description, String location){
        boolean correcto = false;
        ConexionSQLite conex = new ConexionSQLite(context);
        SQLiteDatabase db = conex.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + TABLE_MEDIOS + " SET idenMB = '" + IdenMB + "', type = '" + type + "', Description = '" + description + "', Location = '" + location + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    public boolean eliminarMedio(int id) {

        boolean correcto = false;

        ConexionSQLite conex = new ConexionSQLite(context);
        SQLiteDatabase db = conex.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_MEDIOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
    public long insertarMedioCM(String fecha, String idenMB, String type, String description, String location) {
        long id = 0;
        try {
            ConexionSQLite conex = new ConexionSQLite(context);
            SQLiteDatabase db = conex.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("fecha", fecha);
            values.put("idenMB", idenMB);
            values.put("type", type);
            values.put("description", description);
            values.put("location", location);
            id = db.insert(TABLE_COUNTMENSUAL, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }
    public Medios obtenerMediosCM(String idenMB){
        ConexionSQLite conex = new ConexionSQLite(context);
        SQLiteDatabase db =conex.getWritableDatabase();
        Medios medios = null;
        Cursor cursorMedios;
        cursorMedios = db.rawQuery("SELECT id, rtrim(idenMB), type, description, location FROM "+ TABLE_MEDIOS + " WHERE rtrim(idenMB) = '"+ idenMB +"'", null);
        if (cursorMedios.moveToFirst()){
            medios = new Medios();
            medios.setId(cursorMedios.getInt(0));
            medios.setIdenMB(cursorMedios.getString(1));
            medios.setType(cursorMedios.getString(2));
            medios.setDescription(cursorMedios.getString(3));
            medios.setLocation(cursorMedios.getString(4));
        }
        cursorMedios.close();
        return medios;
    }

    public ConteoMensual ElementoExistente(String fecha, String idenMB){
        boolean correcto = false;
        ConexionSQLite conex = new ConexionSQLite(context);
        SQLiteDatabase db =conex.getWritableDatabase();
        ConteoMensual conteoMensual = null;
        Cursor cursorConteoMensual;
        cursorConteoMensual = db.rawQuery("SELECT * FROM "+ TABLE_COUNTMENSUAL + " WHERE fecha = '"+ fecha.trim() +"'AND idenMB = '"+ idenMB.trim() +"'", null);
        if (cursorConteoMensual.moveToFirst()){
                conteoMensual = new ConteoMensual();
                conteoMensual.setId(cursorConteoMensual.getInt(0));
                conteoMensual.setFecha(cursorConteoMensual.getString(1));
                conteoMensual.setIdenMB(cursorConteoMensual.getString(2));
                conteoMensual.setTypeMB(cursorConteoMensual.getString(3));
                conteoMensual.setDescriptionMB(cursorConteoMensual.getString(4));
                conteoMensual.setLocationMB(cursorConteoMensual.getString(5));
        }
        cursorConteoMensual.close();
        return  conteoMensual;
    }

    public ArrayList<ConteoMensual> mostrarMediosInv() {
        ConexionSQLite conex = new ConexionSQLite(context);
        SQLiteDatabase db = conex.getWritableDatabase();
        ArrayList<ConteoMensual> listaMedios = new ArrayList<>();
        ConteoMensual medios;
        Cursor cursorConteoMensual;
        cursorConteoMensual = db.rawQuery("SELECT * FROM " + TABLE_COUNTMENSUAL, null);
        if (cursorConteoMensual.moveToFirst()) {
            do {
                medios = new ConteoMensual();
                medios.setId(cursorConteoMensual.getInt(0));
                medios.setFecha(cursorConteoMensual.getString(1));
                medios.setIdenMB(cursorConteoMensual.getString(2));
                medios.setTypeMB(cursorConteoMensual.getString(3));
                medios.setDescriptionMB(cursorConteoMensual.getString(4));
                medios.setLocationMB(cursorConteoMensual.getString(5));
                listaMedios.add(medios);
            } while (cursorConteoMensual.moveToNext());
        }
        cursorConteoMensual.close();
        return listaMedios;
    }

    public void backup(String outFilename){
        final String inFilename = context.getDatabasePath("medios.db").toString();
        try {
            File dbFile = new File(inFilename);
            FileInputStream fis = new FileInputStream(dbFile);
            OutputStream outputStream = new FileOutputStream(outFilename);

            byte[] buffer = new byte[1024];
            int length;
            while((length = fis.read(buffer)) > 0){
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            fis.close();
        } catch (Exception e){
            Toast.makeText(context, "Unable to backup database. Retry", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}
