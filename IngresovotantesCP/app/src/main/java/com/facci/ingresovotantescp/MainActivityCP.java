package com.facci.ingresovotantescp;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityCP extends AppCompatActivity {

    DBHelper dbSQLITE;

    EditText txtNombre,txtApellido,txtRecinto,txtNacimiento,txtID;

    Button btnInsertar;
    Button btnModificar;
    Button btnEliminar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_cp);

        dbSQLITE = new DBHelper(this);
    }
    public void insertarClick(View v){


        txtNombre =(EditText) findViewById(R.id.txtNombre);
        txtApellido=(EditText) findViewById(R.id.txtApellido);
        txtRecinto =(EditText) findViewById(R.id.txtRecinto);
        txtNacimiento =(EditText) findViewById(R.id.txtNacimiento);

        boolean estaInsertedo = dbSQLITE.insertar(txtNombre.getText().toString(),txtApellido.getText().toString(),txtRecinto.getText().toString(),Integer.parseInt(txtNacimiento.getText().toString()));
        if (estaInsertedo)
            Toast.makeText(MainActivityCP.this,"Datos Ingresados",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivityCP.this,"Ocurrio un error",Toast.LENGTH_SHORT).show();
    }

    public void verTodosClick(View v){
        Cursor res = dbSQLITE.selectVerTodos();
        if(res.getCount() == 0){
            mostrarMensaje("Error","No se encontraron registros");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Id : "+res.getString(0)+"\n");
            buffer.append("Nombre : "+res.getString(1)+"\n");
            buffer.append("Apellido : "+res.getString(2)+"\n");
            buffer.append("Recinto : "+res.getString(3)+"\n");
            buffer.append("Nacimiento : "+res.getInt(4)+"\n\n");
        }
        mostrarMensaje("Registro",buffer.toString());
    }
    public void mostrarMensaje(String titulo,String Mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();
    }
    public void modificarRegistroClick(View v){

        txtID =(EditText) findViewById(R.id.txtID);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtRecinto = (EditText) findViewById(R.id.txtRecinto);
        txtNacimiento = (EditText) findViewById(R.id.txtNacimiento);


        boolean estaAcutalizado = dbSQLITE.modificarRegistro(txtID.getText().toString(),txtNombre.getText().toString(),txtApellido.getText().toString(),txtRecinto.getText().toString(),Integer.parseInt(txtNacimiento.getText().toString()));


        if (estaAcutalizado == true){
            Toast.makeText(MainActivityCP.this,"Registro Actualizado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityCP.this,"ERROR: Registro NO Actualizado",Toast.LENGTH_SHORT).show();
        }
    }


      public void eliminarRegistroClick(View v){


        txtID = (EditText) findViewById(R.id.txtID);


        Integer registrosEliminados = dbSQLITE.eliminarRegistro(txtID.getText().toString());

        if(registrosEliminados > 0 ){
            Toast.makeText(MainActivityCP.this,"Registro(s) Eliminado(s)",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivityCP.this,"ERROR: Registro no eliminado",Toast.LENGTH_SHORT).show();
        }

    }

}
