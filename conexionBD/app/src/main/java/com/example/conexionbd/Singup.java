package com.example.conexionbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.conexionbd.clases.connectionBD;

public class Singup extends AppCompatActivity {

    EditText Fname, Lname, Email, Passwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        Fname = findViewById(R.id.idFname);
        Lname = findViewById(R.id.idLname);
        Email = findViewById(R.id.idEmail);
        Passwd = findViewById(R.id.idPassword);
    }

    public void Signup(View view){
        //crear el asistente de conexion
        connectionBD manager = new connectionBD( this, "market", null, 1);
        //leer y escribir en la BD
        SQLiteDatabase market = manager.getWritableDatabase();
        //Obtener valores
        String FNAME = Fname.getText().toString();
        String LNAME = Lname.getText().toString();;
        String EMAIL = Email.getText().toString();;
        String PASSWD = Passwd.getText().toString();;

        if (!FNAME.isEmpty() && !LNAME.isEmpty() && !EMAIL.isEmpty() && !PASSWD.isEmpty()){

            //Validacion: No repetir Email si existe
            Cursor row = market.rawQuery("SELECT email FROM users WHERE email = '" + EMAIL + "'", null);

            //if (row.moveToFirst())
            if (row.getCount() > 0){
                Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
            } else {
                ContentValues DATA = new ContentValues();

                DATA.put("firstname", FNAME);
                DATA.put("lastname", LNAME);
                DATA.put("email", EMAIL);
                DATA.put("password", PASSWD);

                //Guardar valores en BD
                market.insert("users", null, DATA);
                market.close();
                Fname.setText("");
                Lname.setText("");
                Email.setText("");
                Passwd.setText("");
                Toast.makeText(this, "El usuario fue creado", Toast.LENGTH_SHORT).show();

            }
        }else{
            Toast.makeText(this, "Hya campos vacios", Toast.LENGTH_SHORT).show();
            Fname.setError("El campo no puede ser vacio");
            Lname.setError("El campo no puede ser vacio");
            Email.setError("El campo no puede ser vacio");
            Passwd.setError("El campo no puede ser vacio");
        }

    }
}
