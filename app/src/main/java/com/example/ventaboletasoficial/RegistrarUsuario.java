package com.example.ventaboletasoficial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        //Construir código para insertar registro y retornar
        //True: registro insertado, false en cualquier otro caso

        return true;
    }
}