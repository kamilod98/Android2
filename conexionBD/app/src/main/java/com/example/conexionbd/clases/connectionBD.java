package com.example.conexionbd.clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.conexionbd.Singup;

public class connectionBD extends SQLiteOpenHelper{
    public connectionBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase market) {
        //Crear las tablas de la bd
        market.execSQL("create table users(id primary key not null," +
                "firstname text not null, lastname text not null, " +
                "email text not null, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
