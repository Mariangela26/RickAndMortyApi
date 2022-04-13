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
import com.example.rickandmortyapi.clases.Utils;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    List<ListElement> elementsFav;
    ConexionSqliteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        conn = new ConexionSqliteHelper(getApplicationContext(), "db_personajes", null,1);
        listarCards();
    }

    private void listarCards() {
        elementsFav = new ArrayList<>();
        String name, species, status, gender;

        SQLiteDatabase db = conn.getReadableDatabase();
        String[] fields = {Utils.CAMPO_NAME, Utils.CAMPO_SPECIES, Utils.CAMPO_STATUS, Utils.CAMPO_GENDER};
        Cursor cursor = db.query(Utils.TABLE_PERSONAJES, fields, null, null,null,null,null);

        for(int i=0; i<3; i++){

            try {
                cursor.move(i);

                name = cursor.getString(0);
                species = cursor.getString(1);
                status = cursor.getString(2);
                gender = cursor.getString(3);
                elementsFav.add(new ListElement(name, species, status, gender));
                System.out.println("LISTA fav: "+elementsFav.get(i).name+", "+elementsFav.get(i).status);

                cursor.close();

            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "no existen favritos.", Toast.LENGTH_LONG).show();
            }


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