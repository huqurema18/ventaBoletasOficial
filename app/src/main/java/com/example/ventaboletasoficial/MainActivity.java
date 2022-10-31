package com.example.ventaboletasoficial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ventaboletasoficial.BD.DbHelper;
import com.example.ventaboletasoficial.BD.constantes;

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
        
        consultarUsuario();

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

    private void consultarVentas() {
        try {
            //conexión
            DbHelper dbHelper = new DbHelper(this);
            //objeto para la lectura en la base de datos
            SQLiteDatabase base_datos = dbHelper.getReadableDatabase();

    }catch (Exception e){}
    }

    private boolean autenticarUsuario(){
        //Construir código para validar usuario y retornar
        //True: registro encontrado, false en cualquier otro caso

        return true;//Valor por default para funcionamiento inicial
    }



    private void consultarUsuario() {
        try {
            //conexión
            DbHelper dbHelper = new DbHelper(this);
            //objeto para la lectura en la base de datos
            SQLiteDatabase base_datos = dbHelper.getReadableDatabase();
            //Arreglo con las condiciones de búsqueda, WHERE
            String[] parametrosConsulta = {WEmail.getText().toString()};
            //Arreglo con los camps a consultar, SELECT
            String[] camposConsulta = {"NOMBRE"};
            //Se define cursor para almacenar el resultado de la búsqueda
            Cursor cursor = base_datos.query(constantes.TABLA_USUARIOS, camposConsulta, "EMAIL" + "=?",
                    parametrosConsulta, null, null, null);
            cursor.moveToFirst();
            Toast.makeText(this, "Bienvenido " + cursor.getString(0), Toast.LENGTH_SHORT).show();

            cursor.close();
        } catch (Exception e) {
            Toast.makeText(this, "Error al consultar Usuario", Toast.LENGTH_SHORT).show();
            WEmail.setText("");
        }
    }

    private void actualizarNombreUsuario() {
        try {
            //Conexión
            DbHelper helper = new DbHelper(this);
            //Lectura BD
            SQLiteDatabase base_datos = helper.getWritableDatabase();
            //Where
            String[] parametrosConsulta = {"aqui va lo de consultar"};
            //Nuevos valores (actualización)
            ContentValues values = new ContentValues();
            values.put("NOMBRE", "Nombre actualiazado");
            base_datos.update("USUARIOS", values, "EMAIL" + "=?", parametrosConsulta);
            base_datos.close();
            //Confirmación
            Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(this, "Error al actualizar usuario", Toast.LENGTH_SHORT).show();

        }
    }

    private void eliminarUsuario() {
        try {
            //Conexión
            DbHelper helper = new DbHelper(this);
            //Lectura BD
            SQLiteDatabase base_datos = helper.getWritableDatabase();
            //Where
            String[] parametrosConsulta = {"aqui va lo de consultar"};
            base_datos.delete("USUARIOS", "EMAIL" + "=?", parametrosConsulta);
            base_datos.close();
            //Confirmación
            Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(this, "Error al eliminar usuario", Toast.LENGTH_SHORT).show();
        }
    }
}