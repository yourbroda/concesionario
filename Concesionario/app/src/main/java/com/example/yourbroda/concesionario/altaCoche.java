package com.example.yourbroda.concesionario;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.ArrayList;

public class altaCoche extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "Bases",null,1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_coche);
        Button btBuscar=(Button)findViewById(R.id.btBuscar);
        Button btAceptar=(Button)findViewById(R.id.btAceptar);
        EditText etMatricula=(EditText)findViewById(R.id.etMatricula);
        EditText etMarca=(EditText)findViewById(R.id.etMarca);
        EditText etPotencia=(EditText)findViewById(R.id.etPotencia);
        TextView tvDni=(TextView)findViewById(R.id.tvDni);
        Spinner spDni=(Spinner)findViewById(R.id.spDni);
        Bundle bundle=getIntent().getExtras();
        ArrayList<String>dni=new ArrayList<String>();
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, dni);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDni.setAdapter(adapter);
        Cursor registro=bd.rawQuery(
                "select dni from propietarios ", null);
        if(registro.moveToFirst()){
            do{
                dni.add(registro.getString(0));
            }while(registro.moveToNext());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alta_coche, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
