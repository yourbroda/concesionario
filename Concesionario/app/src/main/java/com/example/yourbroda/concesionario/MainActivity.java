package com.example.yourbroda.concesionario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioButton rbCoches;
    RadioButton rbPropietarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btAltas=(Button)findViewById(R.id.btAltas);
        Button btBajas=(Button)findViewById(R.id.btBajas);
        Button btModificar=(Button)findViewById(R.id.btModificar);
        Button btConsultar=(Button)findViewById(R.id.btConsultas);
        rbPropietarios=(RadioButton)findViewById(R.id.rbPropietarios);
        rbCoches=(RadioButton)findViewById(R.id.rbCoches);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void alta(View v){
        try{
            if(rbPropietarios.isChecked()){
                Intent i=new Intent(this,AlBaMo.class);
                i.putExtra("opcion","alta");
                startActivity(i);
            }
            if(rbCoches.isChecked()){
                Intent i=new  Intent(this,altaCoche.class);
                i.putExtra("opcion","alta");
                startActivity(i);
            }
        }catch(Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
    public void baja(View v){
        if(rbPropietarios.isChecked()){
            Intent i=new Intent(this, AlBaMo.class);
            i.putExtra("opcion","baja");
            startActivity(i);
        }
        if(rbCoches.isChecked()){
            Intent i=new  Intent(this,Consultas.class);
            i.putExtra("opcion","baja");
            startActivity(i);
        }

    }
    public void modificar(View v){
        if(rbPropietarios.isChecked()){
            Intent i=new Intent(this,AlBaMo.class);
            i.putExtra("opcion", "modi");
            startActivity(i);
        }if(rbCoches.isChecked()){
            Intent i=new Intent (this, altaCoche.class);
            i.putExtra("opcion","modi");
            startActivity(i);
        }
    }
    public void consulta(View v){
        if(rbPropietarios.isChecked()){
            Toast.makeText(this,"Consultas solo para coches", Toast.LENGTH_SHORT).show();
        }
        if(rbCoches.isChecked()){
            Intent i=new Intent(this,Consultas.class);
            i.putExtra("opcion","consulta");
        }
    }
}
