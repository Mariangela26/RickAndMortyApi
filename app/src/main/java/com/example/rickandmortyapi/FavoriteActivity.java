package com.example.rickandmortyapi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rickandmortyapi.clases.CharacterC;
import com.example.rickandmortyapi.clases.ConexionSqliteHelper;
import com.example.rickandmortyapi.clases.ListAdapter;
import com.example.rickandmortyapi.clases.ListElement;
import com.example.rickandmortyapi.clases.Utils;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    List<ListElement> elementsFav;
    ConexionSqliteHelper conn;
    RecyclerView recyclerViewCharacter;
    CharacterC characterC;

    String selectionFav;
    Bundle bundInfoFav = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerViewCharacter = findViewById(R.id.listRecyclerView);

        conn = new ConexionSqliteHelper(getApplicationContext(), "db_character", null,1);
        listCards();
    }

    private void listCards() {
        elementsFav = new ArrayList<>();

        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+Utils.TABLE_CHARACTER+" WHERE "+ Utils.FIELD_FAVORITE+"=1", null);

        try {
            if(cursor.moveToFirst()){
                do{
                    characterC =new CharacterC();
                    characterC.setId(cursor.getInt(0));
                    characterC.setName(cursor.getString(1));
                    characterC.setStatus(cursor.getString(2));
                    characterC.setSpecies(cursor.getString(3));
                    characterC.setType(cursor.getString(4));
                    characterC.setGender(cursor.getString(5));
                    characterC.setUrlImage(cursor.getString(6));
                    characterC.setUrlCharacter(cursor.getString(7));
                    characterC.setCreated(cursor.getString(8));

                    elementsFav.add(new ListElement(characterC.getName(), characterC.getSpecies(), characterC.getStatus(), characterC.getGender()));

                }
                while (cursor.moveToNext());
            }


        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "No existen favoritos.", Toast.LENGTH_LONG).show();
        }


        try {
            ListAdapter listAdapter = new ListAdapter(elementsFav, this);
            RecyclerView recyclerView = findViewById(R.id.listRecyclerViewFav);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(listAdapter);

            listAdapter.setOnclickListener(new  View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectionFav = elementsFav.get(recyclerView.getChildAdapterPosition(view)).getName();
                    bundInfoFav.putString("name", selectionFav);

                    Intent intent2 = new Intent(FavoriteActivity.this, InfoActivity.class);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent2.putExtras(bundInfoFav);
                    startActivity(intent2);
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "No existen favoritos", Toast.LENGTH_LONG).show();
        }



    }
}