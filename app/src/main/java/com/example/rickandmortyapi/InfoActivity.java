package com.example.rickandmortyapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.rickandmortyapi.clases.ConexionSqliteHelper;
import com.example.rickandmortyapi.clases.Personaje;
import com.example.rickandmortyapi.clases.Utils;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    private TextView txId, txName, txStatus, txSpecies, txType, txGender, txOrigin, txLocation, txUrl, txCreated;
    private String id, name, status, species, type, gender, origin, location, url, created;

    ArrayList<Personaje> listPersonaje;
    ConexionSqliteHelper conn ;
    Personaje personaje = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        conn = new ConexionSqliteHelper(getApplicationContext(), "db_Personajes", null, 1);
        listPersonaje = new ArrayList<>();

        Bundle bundInfoReceived = this.getIntent().getExtras();

        txId = findViewById(R.id.txId);
        txName = findViewById(R.id.txName);
        txStatus = findViewById(R.id.txStatus);
        txSpecies = findViewById(R.id.txSpecies);
        txType = findViewById(R.id.txType);
        txGender = findViewById(R.id.txGender);
        txOrigin = findViewById(R.id.txOrigin);
        txLocation = findViewById(R.id.txLocation);
        txUrl = findViewById(R.id.txUrl);
        txCreated = findViewById(R.id.txCreated);

        name = bundInfoReceived.getString("name");
        System.out.println("este es el nombre: "+name);


        showInfo(findByName(name));

    }

    public Personaje findByName(String name){

        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+Utils.TABLE_PERSONAJES+" WHERE "
                        + Utils.CAMPO_NAME+"=\"Morty Smith\"", null);

        if(cursor.moveToFirst()){
            do{
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

                listPersonaje.add(personaje);
            }while (cursor.moveToNext());
        }
            return personaje;

    }

    private void showInfo(Personaje per) {

        txId.setText(txId.getText().toString()+per.getId());
        txName.setText(txName.getText()+per.getName());
        txStatus.setText(txStatus.getText()+per.getStatus());
        txSpecies.setText(txSpecies.getText()+per.getSpecies());
        txType.setText(txType.getText()+per.getType());
        txGender.setText(txGender.getText()+per.getGender());
        //txOrigin.setText(txOrigin.getText()+per.getOrigin().getName());
        //txLocation.setText(txLocation.getText()+per.getLocation().getName());
        txUrl.setText(txUrl.getText()+per.getUrlCharacter());
        txCreated.setText(txCreated.getText()+per.getCreated());

    }

    public void onClick(View view) {
        ConexionSqliteHelper conn = new ConexionSqliteHelper(this, "db_Fav", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utils.CAMPO_ID,txId.getText().toString());
        values.put(Utils.CAMPO_NAME,txName.getText().toString());
        values.put(Utils.CAMPO_STATUS,txStatus.getText().toString());
        values.put(Utils.CAMPO_SPECIES,txSpecies.getText().toString());
        values.put(Utils.CAMPO_TYPE,txType.getText().toString());
        values.put(Utils.CAMPO_GENDER,txGender.getText().toString());
        values.put(Utils.CAMPO_URL_IMAGE,txUrl.getText().toString());
        values.put(Utils.CAMPO_URL_CHARACTER,txUrl.getText().toString());
        values.put(Utils.CAMPO_CREATED,txCreated.getText().toString());

        db.close();
    }
}