package com.facci.proyectoiwmc;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityIWMC extends AppCompatActivity {
    DBHelper  dbSQLITE;
    EditText ID, Nombre, Apellido, RecintoElectoral, AñoNacimiento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_iwmc);

        dbSQLITE = new DBHelper(this);
    }
    public void insertar(View v) {

        Nombre = (EditText) findViewById(R.id.txtNombre);
        Apellido = (EditText) findViewById(R.id.txtApellido);
        RecintoElectoral = (EditText) findViewById(R.id.txtRecintoElectoral);
        AñoNacimiento = (EditText) findViewById(R.id.txtAñoNacimiento);

        boolean Insertadatos = dbSQLITE.Insertar(Nombre.getText().toString(), Apellido.getText().toString(), RecintoElectoral.getText().toString(), Integer.parseInt(AñoNacimiento.getText().toString()));

        if (Insertadatos) {
            Toast.makeText(MainActivityIWMC.this, "Datos ingresados", Toast.LENGTH_SHORT).show();
        }else{Toast.makeText(MainActivityIWMC.this,"Datos no ingresados",Toast.LENGTH_SHORT).show();}
    }
    public void buscar (View v) {
        Cursor res = dbSQLITE.VerTodos();

        if (res.getCount() == 0) {
            Mensaje("Error","No se encontraron registros");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Id : "+res.getString(0)+"\n");
            buffer.append("Nombre : "+res.getString(1)+"\n");
            buffer.append("Apellido : "+res.getString(2)+"\n");
            buffer.append("Recinto Electoral : "+res.getString(3)+"\n");
            buffer.append("Año de Nacimiento : "+res.getInt(4)+"\n\n");
        }
        Mensaje("Registros",buffer.toString());
    }
    private void Mensaje (String titulo, String Mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();
    }
    public void modificar (View v) {
        Nombre = (EditText) findViewById(R.id.txtNombre);
        Apellido= (EditText) findViewById(R.id.txtApellido);
        RecintoElectoral = (EditText) findViewById(R.id.txtRecintoElectoral);
        AñoNacimiento = (EditText) findViewById(R.id.txtAñoNacimiento);
        ID = (EditText) findViewById(R.id.txtID);
        boolean ActualizandoDatos = dbSQLITE.Modificar(ID.getText().toString(), Nombre.getText().toString(), Apellido.getText().toString(), RecintoElectoral.getText().toString(), Integer.parseInt(AñoNacimiento.getText().toString()));
        if(ActualizandoDatos)
            Toast.makeText(MainActivityIWMC.this,"Datos Ingresados",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivityIWMC.this,"Hubo un error", Toast.LENGTH_SHORT).show();

    }
    public void eliminar (View v) {
        ID = (EditText) findViewById(R.id.txtID);

        Integer registrosEliminados = dbSQLITE.Eliminar(ID.getText().toString());

        if(registrosEliminados > 0 ){
            Toast.makeText(MainActivityIWMC.this,"Registros Eliminados",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityIWMC.this,"Registros no eliminados",Toast.LENGTH_SHORT).show();
        }
    }
}
