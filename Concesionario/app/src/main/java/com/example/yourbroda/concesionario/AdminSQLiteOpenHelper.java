package com.example.yourbroda.concesionario;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table propietarios(" +
                "dni text primary key," +
                "nombre text," +
                "edad int)");
        db.execSQL("create table coches(" +
                "matricula text ," +
                "marca text," +
                "potencia int," +
                "dni text,"+
                "primary key (matricula)," +
                "foreign key (dni)references propietarios(dni) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
