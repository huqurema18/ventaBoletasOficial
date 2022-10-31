package com.example.ventaboletasoficial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnIniciarSesion;
    EditText txtDocumento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relación de objetos con Layout
        txtDocumento = findViewById(R.id.txtNumDocumento);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);

        //Iniciar sesión
        btnIniciarSesion.setOnClickListener(v->{
            //Consulta existencia del documento en la BD, si existe procede a la venta, cuando no exista, debe registrarse
            try{
                if(autenticarUsuario()){
                    //El usuario existe, debe enviarse el documento como parámetro al Intent de ventas
                    Toast.makeText(this, "Bienvenid@", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, VentaBoletas.class);
                    intent.putExtra("documento",txtDocumento.getText());
                    startActivity(intent);
                }
                else {
                    //No existe, debe crear el registro, luego del registro, podrá comprar
                    Toast.makeText(this, "Usuario inexistente, por favor, registre sus datos", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, RegistrarUsuario.class);
                    intent.putExtra("documento",txtDocumento.getText());
                    startActivity(intent);
                }
            }
            catch  (Exception e){
                Toast.makeText(this, "Error, no fue posible iniciar sesión", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private boolean autenticarUsuario(){
        //Construir código para validar usuario y retornar
        //True: registro encontrado, false en cualquier otro caso

        return true;//Valor por default para funcionamiento inicial
    }
}