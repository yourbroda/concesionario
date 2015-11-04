package com.example.yourbroda.concesionario;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlBaMo extends AppCompatActivity {
    Button btGrabar;
    EditText etNombre;
    EditText etDni;
    EditText etEdad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_al_ba_mo);
        Bundle bundle= getIntent().getExtras();
        String opc= bundle.getString("opcion");
        btGrabar=(Button)findViewById(R.id.btGrabar);
        Button btAceptar=(Button)findViewById(R.id.btAceptar);
        Button btCancelar=(Button)findViewById(R.id.btSalir);
        etNombre=(EditText)findViewById(R.id.etNombre);
        etDni=(EditText)findViewById(R.id.etDni);
        etEdad=(EditText)findViewById(R.id.etEdad);
        if(opc.compareTo("alta")==0){
            btGrabar.setText("Grabar");
        }
        if(opc.compareTo("baja")==0){
            btGrabar.setText("Borrar");
        }
        if(opc.compareTo("modi")==0){
            btGrabar.setText(("Modificar"));
            btGrabar.setEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_al_ba_mo, menu);
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
    public void agregar(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "Bases", null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        bd.execSQL("PRAGMA foreign_keys=ON;");

        if(btGrabar.getText().toString().compareTo("Grabar")==0){
            try{

            String nombre=etNombre.getText().toString();
            String dni=etDni.getText().toString();
            int edad=Integer.parseInt(etEdad.getText().toString());

            ContentValues registro=new ContentValues();

                registro.put("dni",dni);
                registro.put("nombre", nombre);
                registro.put("edad", edad);
                Long val=bd.insert("propietarios", null, registro);
                if(val!=-1){
                    Toast.makeText(this, "Se han cargado bien los datos", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "No se ha insertado", Toast.LENGTH_SHORT).show();
                }

                bd.close();
            }catch(Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }



        }
        if(btGrabar.getText().toString().compareTo("Borrar")==0){
            bd.execSQL("PRAGMA foreign_keys=OFF;");
            String dni=etDni.getText().toString();
            try{

                bd.delete("propietarios", "dni='"+dni+"'", null);
                bd.delete("coches", "dni='"+dni+"'", null);
                Toast.makeText(this, "Se borraron correctamente los propietarios y" +
                        " los coches asociados", Toast.LENGTH_SHORT).show();
            }catch(Exception e){
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        if (btGrabar.getText().toString().compareTo("Modificar")==0 ) {


            String dni=etDni.getText().toString();

            ContentValues registro=new ContentValues();
            String nombre=etNombre.getText().toString();
            int edad=Integer.parseInt(etEdad.getText().toString());
            registro.put("nombre", nombre);
            registro.put("edad", edad);

            try{
                int val=bd.update("propietarios", registro, "dni='"+dni+"'",null);

                if(val==1){
                    Toast.makeText(this, "Han sido modificados este numoer de registros"+val,
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, /*"no existe una persona con el dni ingresado"*/dni,
                            Toast.LENGTH_SHORT).show();
                }
                bd.close();
            }catch (Exception e){
                Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void rellenar(View v){
        btGrabar.setEnabled(true);
        try{
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                    "Bases", null, 1);
            SQLiteDatabase bd=admin.getWritableDatabase();
            String dni= etDni.getText().toString();
            Cursor fila=bd.rawQuery(
                    "select nombre,edad from propietarios where dni='" + dni+"'", null);

            if(fila.moveToFirst()){
                etNombre.setText(fila.getString(0));
                etEdad.setText(fila.getString(1));
            }else{
                Toast.makeText(this, "No hay una persona con este Dni", Toast.LENGTH_SHORT).show();
            }
            bd.close();
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        }



    }
    public void salir(View v){
        finish();
    }
}

