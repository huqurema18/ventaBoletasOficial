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

    private void registrarVentaBD() {
        try {
            //Conexion a la BD
            DbHelper dbHelper=new DbHelper(this);
            //Objeto para la interaccion con la BD
            SQLiteDatabase datos =dbHelper.getWritableDatabase();
            //permite hacer insercion al combinar clave-valor
            ContentValues values=new ContentValues();
            values.put("NOMBRE", txNombre.getText().toString());
            values.put("EMAIL", txEmail.getText().toString());

            //Inserta el registro y retorna el ID
            long id=datos.insert(constantes.TABLA_USUARIOS,null, values);

            if(id>0){
                Toast.makeText(this, "USUARIO CREADO", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "ERROR AL CREAR EL USUARIO", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, "NO FUE POSIBLE REGISTRAR LA INFORMACIÓN", Toast.LENGTH_SHORT).show();
        }
    }
}