package com.example.ventaboletasoficial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VentaBoletas extends AppCompatActivity {

    EditText txtNumAsientos;
    Button btnComprar,btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta_boletas);

        //Conexión objetos con layout
        btnComprar = findViewById(R.id.btnComprar);
        btnRegresar = findViewById(R.id.btnRegresaCompra);
        txtNumAsientos = findViewById(R.id.txtNumAsientos);

        //Regresar
        btnRegresar.setOnClickListener(v->{
            //Regresa al Intent principal
            startActivity(new Intent(this,MainActivity.class));
        });

        //Registra compra
        btnComprar.setOnClickListener(v->{
            try{
                //Registra la venta al usuario

            }
            catch  (Exception e){
                Toast.makeText(this, "Error, no fue posible registr la compra", Toast.LENGTH_SHORT).show();
            }
        });
    }
}