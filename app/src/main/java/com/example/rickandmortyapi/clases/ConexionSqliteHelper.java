package com.example.rickandmortyapi.clases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class ConexionSqliteHelper extends SQLiteOpenHelper{

    public ConexionSqliteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Utils.CREATE_TABLE_CHARACTER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+Utils.TABLE_CHARACTER);
        onCreate(db);

    }
}
