package com.example.rickandmortyapi;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rickandmortyapi.clases.CharacterC;
import com.example.rickandmortyapi.clases.ConexionSqliteHelper;
import com.example.rickandmortyapi.clases.Utils;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {

    private TextView txId, txName, txStatus, txSpecies, txType, txGender, txUrl, txCreated;
    private String name;
    private Button btnAdd, btnDelete;

    ArrayList<CharacterC> listCharacterC;
    ConexionSqliteHelper conn ;
    CharacterC characterC = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        conn = new ConexionSqliteHelper(getApplicationContext(), "db_character", null, 1);
        listCharacterC = new ArrayList<>();

        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        Bundle bundInfoReceived = this.getIntent().getExtras();

        txId = findViewById(R.id.txId);
        txName = findViewById(R.id.txName);
        txStatus = findViewById(R.id.txStatus);
        txSpecies = findViewById(R.id.txSpecies);
        txType = findViewById(R.id.txType);
        txGender = findViewById(R.id.txGender);
        txUrl = findViewById(R.id.txUrl);
        txCreated = findViewById(R.id.txCreated);

        name = bundInfoReceived.getString("name");

        showInfo(findByName(name));

    }

    public CharacterC findByName(String name){

        SQLiteDatabase db = conn.getReadableDatabase();
        name +="\"";
        Cursor cursor = db.rawQuery("SELECT * FROM "+Utils.TABLE_CHARACTER +" WHERE "
                + Utils.FIELD_NAME +"=\""+name, null);

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
                characterC.setFavorite(cursor.getInt(9));

                listCharacterC.add(characterC);
            }while (cursor.moveToNext());
        }
            return characterC;

    }

    private void showInfo(CharacterC characterC) {

        txId.setText(txId.getText().toString()+characterC.getId());
        txName.setText(txName.getText()+characterC.getName());
        txStatus.setText(txStatus.getText()+characterC.getStatus());
        txSpecies.setText(txSpecies.getText()+characterC.getSpecies());
        txType.setText(txType.getText()+characterC.getType());
        txGender.setText(txGender.getText()+characterC.getGender());
        txUrl.setText(txUrl.getText()+characterC.getUrlCharacter());
        txCreated.setText(txCreated.getText()+characterC.getCreated());

        if(characterC.getFavorite()==0){
            btnAdd.setEnabled(true);
        }else{
            btnDelete.setEnabled(true);
        }

    }

    public void onClick(View view) {

        ConexionSqliteHelper conn = new ConexionSqliteHelper(this, "db_character", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        String id = txId.getText().toString().split(":")[1];
        String[] parameter = {id};
        ContentValues values = new ContentValues();
        if(view.getId()==R.id.btnAdd){
            values.put(Utils.FIELD_FAVORITE, 1);
            btnDelete.setEnabled(true);
            btnAdd.setEnabled(false);
        }else{
            values.put(Utils.FIELD_FAVORITE, 0);
            btnDelete.setEnabled(false);
            btnAdd.setEnabled(true);
        }


        db.update(Utils.TABLE_CHARACTER,values, Utils.FIELD_ID+"=?", parameter);



        db.close();
    }
}