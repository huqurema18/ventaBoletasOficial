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
                if(insertarVenta()){
                    //Confirma y envía a la actividad de reserva
                    Toast.makeText(this, "Registro de venta creado correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, VentaBoletas.class);
                    intent.putExtra("documento",txtNumAsientos.getText());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, "No fue posible guardar la venta, por favor intente nuevamente", Toast.LENGTH_LONG).show();
                }
            }
            catch  (Exception e){
                Toast.makeText(this, "Error, no fue posible insertar la venta", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean insertarVenta(){
        //Construir código para insertar registro y retornar
        //True: registro insertado, false en cualquier otro caso

        return true;
    }
}