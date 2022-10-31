package com.example.ventaboletasoficial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ventaboletasoficial.BD.DbHelper;
import com.example.ventaboletasoficial.BD.constantes;

public class RegistrarUsuario extends AppCompatActivity {

    EditText txtDocumento, txtNombre, txtTelefono;
    Button btnRegistro,btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        //Relación con Layout
        txtDocumento = findViewById(R.id.txtDocumento);
        txtNombre=findViewById(R.id.txtNombre);
        txtTelefono=findViewById(R.id.txtTelefono);
        btnRegistro=findViewById(R.id.btnRegistro);
        btnRegresar=findViewById(R.id.btnRegresar);

        //Regresar
        btnRegresar.setOnClickListener(v->{
            //Regresa al Intent principal
            startActivity(new Intent(this,MainActivity.class));
        });

        //Creación del usuario
        btnRegistro.setOnClickListener(v->{
            //Inserta el nuevo registro en la BD, al recibir confirmación, permite el registro de la venta
            try{
                if(insertarUsuario()){
                    //Confirma y envía a la actividad de reserva
                    Toast.makeText(this, "Registro creado correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, VentaBoletas.class);
                    intent.putExtra("documento",txtDocumento.getText());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, "No fue posible guardar la información, por favor intente nuevamente", Toast.LENGTH_LONG).show();
                }
            }
            catch  (Exception e){
                Toast.makeText(this, "Error, no fue posible insertar el registro", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean insertarUsuario(){
        boolean query=false;
        //Construir código para insertar registro y retornar
        //True: registro insertado, false en cualquier otro caso

        try {
            //Conexion a la BD
            DbHelper dbHelper=new DbHelper(this);
            //Objeto para la interaccion con la BD
            SQLiteDatabase datos =dbHelper.getWritableDatabase();
            //permite hacer insercion al combinar clave-valor
            ContentValues values=new ContentValues();
            values.put("DOCUMENTO", txtDocumento.getText().toString());
            values.put("NOMBRE", txtNombre.getText().toString());
            values.put("TELEFONO", txtNombre.getText().toString());

            //Inserta el registro y retorna el ID
            long id=datos.insert(constantes.TABLA_USUARIOS,null, values);

            if(id>0){
                Toast.makeText(this, "USUARIO CREADO", Toast.LENGTH_SHORT).show();
                query=true;
            }else{
                Toast.makeText(this, "ERROR AL CREAR EL USUARIO", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "NO FUE POSIBLE REGISTRAR LA INFORMACIÓN", Toast.LENGTH_SHORT).show();
        }

        return query;
    }


}