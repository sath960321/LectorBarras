package com.example.lectorbarras.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "medios.db";
    public static final String TABLE_MEDIOS = "t_medios";
    public static final String TABLE_DEPARTAMENTO = "t_departamento";
    public static final String TABLE_COUNTMENSUAL = "t_countmensual";
    public static final String TABLE_EXPEDIENTEPC = "expediente_pc";

    public ConexionSQLite(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MEDIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idenMB TEXT NOT NULL," +
                "type TEXT NOT NULL," +
                "Description TEXT NOT NULL," +
                "Location TEXT NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_DEPARTAMENTO + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Location TEXT NOT NULL," +
                "Description TEXT NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_COUNTMENSUAL + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fecha TEXT NOT NULL," +
                "idenMB TEXT NOT NULL," +
                "type TEXT NOT NULL," +
                "description TEXT NOT NULL," +
                "location TEXT NOT NULL)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_MEDIOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_DEPARTAMENTO);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_COUNTMENSUAL);
        onCreate(sqLiteDatabase);
    }


}
