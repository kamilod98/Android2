package com.example.conexionbd;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.conexionbd.clases.connectionBD;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class listar_usuarios extends AppCompatActivity {

    //Llamar a la BD
    connectionBD market;
    //Crear variable de ListView
    ListView userlist;
    //Crear un ArrayList
    ArrayList<String> listItem;
    //Crear el ArrayAdapter
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);

        //Intanciar la coneccion a la BD
        market = new connectionBD(this, "market", null, 1);

        //Crear un array vacio
        listItem = new ArrayList<>();

        //Llamar el id ListView
        userlist = findViewById(R.id.idListUsers);

        //Llamar el metodo de ViewData
        viewData();
    }

    private void viewData() {
        Cursor cursor = market.SelectUserData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No hay usuarios", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));
                listItem.add(cursor.getString(2));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            userlist.setAdapter(adapter);
        }
    }
}
