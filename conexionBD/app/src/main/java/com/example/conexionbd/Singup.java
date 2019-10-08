package com.example.conexionbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.conexionbd.clases.connectionBD;

public class Singup extends AppCompatActivity {

    EditText fname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        fname = findViewById(R.id.idFname);
    }

    public void Signup(View view){
        //crear el asistente de conexion
        connectionBD manager = new connectionBD( this, "Data", null, 1);
        //leer y escribir en la BD
        SQLiteDatabase market = manager.getWritableDatabase();
        //Obtener valores
        String Fname = fname.getText().toString();
        String Lname = "";
        String Email = "";
        String Passwd = "";

        if (!Fname.isEmpty()){
            ContentValues DATA = new ContentValues();

            DATA.put("firstname", Fname);
            DATA.put("lastname", Lname);
            DATA.put("email", Email);
            DATA.put("password", Passwd);

            //Guardar valores en BD
            market.insert("users", null, DATA);
            Toast.makeText(this, "El usuario fue creado", Toast.LENGTH_SHORT).show();
            market.close();
        }else{
            Toast.makeText(this, "Debe ingresar un usuario", Toast.LENGTH_SHORT).show();
        }

    }
}
