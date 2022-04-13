package com.example.rickandmortyapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.rickandmortyapi.clases.ConexionSqliteHelper;
import com.example.rickandmortyapi.clases.ListAdapter;
import com.example.rickandmortyapi.clases.ListElement;
import com.example.rickandmortyapi.clases.Personaje;
import com.example.rickandmortyapi.clases.Utils;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    List<ListElement> elementsFav;
    ConexionSqliteHelper conn;
    RecyclerView recyclerViewPersonaje;
    Personaje personaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerViewPersonaje = findViewById(R.id.listRecyclerView);
        recyclerViewPersonaje.setLayoutManager(new LinearLayoutManager(this));

        conn = new ConexionSqliteHelper(getApplicationContext(), "db_favoritos", null,1);
        listarCards();
    }

    private void listarCards() {
        elementsFav = new ArrayList<>();
        String name, species, status, gender;

        SQLiteDatabase db = conn.getReadableDatabase();
        String[] fields = {Utils.CAMPO_NAME, Utils.CAMPO_SPECIES, Utils.CAMPO_STATUS, Utils.CAMPO_GENDER};
        //Cursor cursor = db.query(Utils.TABLE_PERSONAJES, fields, null, null,null,null,null);

        Cursor cursor = db.rawQuery("SELECT * FROM "+Utils.TABLE_PERSONAJES/*+" WHERE "+ Utils.CAMPO_NAME+"="+name*/, null);

        try {
            while (cursor.moveToNext()){
                personaje=new Personaje();
                personaje.setId(cursor.getInt(0));
                personaje.setName(cursor.getString(1));
                personaje.setStatus(cursor.getString(2));
                personaje.setSpecies(cursor.getString(3));
                personaje.setType(cursor.getString(4));
                personaje.setGender(cursor.getString(5));
                personaje.setUrlImage(cursor.getString(6));
                personaje.setUrlCharacter(cursor.getString(7));
                personaje.setCreated(cursor.getString(8));

                elementsFav.add(new ListElement(personaje.getName(), personaje.getSpecies(), personaje.getStatus(), personaje.getGender()));

            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "no existen favritos.", Toast.LENGTH_LONG).show();
        }


        try {
            ListAdapter listAdapter = new ListAdapter(elementsFav, this);
            RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(listAdapter);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "no existen favritos", Toast.LENGTH_LONG).show();
        }



    }
}