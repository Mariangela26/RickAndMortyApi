package com.example.rickandmortyapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rickandmortyapi.clases.ConexionSqliteHelper;
import com.example.rickandmortyapi.clases.ListAdapter;
import com.example.rickandmortyapi.clases.ListElement;
import com.example.rickandmortyapi.clases.Origin;
import com.example.rickandmortyapi.clases.CharacterC;
import com.example.rickandmortyapi.clases.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String urlApi = "https://rickandmortyapi.com/api/character";
    // String urlApi = "https://rickandmortyapi.com/api/character?page=1";
    private Button btnFavoritos;
    List<ListElement> elements;
    String selection;
    Bundle bundInfo = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSqliteHelper conn = new ConexionSqliteHelper(this, "db_Personajes", null, 1);

        btnFavoritos = findViewById(R.id.btnFavoritos);

        cargarDatosApi();

        btnFavoritos.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnFavoritos:
                Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            default:

                break;
        }
    }

    //Hace la peticion a la api
    // TODO: guardar los datos en la bd
    private void cargarDatosApi() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlApi, null, response -> parserJson(response), error -> Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_SHORT).show());
        requestQueue.add(jsonObjectRequest);

    }

    private void parserJson(JSONObject response) {
        CharacterC characterC;
        Origin origin;
        com.example.rickandmorty.Clases.Location location;
        JSONArray episodio;

        try {
            JSONArray arrayPersonajes = response.getJSONArray("results");

            elements = new ArrayList<>();
            String name, species, status, gender, image;

            for(int i = 0; i<arrayPersonajes.length(); i++){
                JSONObject jsnPersonaje = arrayPersonajes.getJSONObject(i);
                origin = new Origin(jsnPersonaje.getJSONObject("origin").getString("name"),
                        jsnPersonaje.getJSONObject("origin").getString("url"));
                location = new com.example.rickandmorty.Clases.Location(jsnPersonaje.getJSONObject("location").getString("name"),
                        jsnPersonaje.getJSONObject("origin").getString("url"));
                episodio=jsnPersonaje.getJSONArray("episode");
                characterC = new CharacterC(jsnPersonaje.getInt("id"),
                        jsnPersonaje.getString("name"),
                        jsnPersonaje.getString("status"),
                        jsnPersonaje.getString("species"),
                        jsnPersonaje.getString("type"),
                        jsnPersonaje.getString("gender"),
                        origin,location,
                        jsnPersonaje.getString("image"),
                        episodio,
                        jsnPersonaje.getString("url"),
                        jsnPersonaje.getString("created"));

               saveDB(jsnPersonaje);

                name = characterC.getName();
                species = characterC.getSpecies();
                status = characterC.getStatus();
                gender = characterC.getGender();
                image = characterC.getUrlImage();
                elements.add(new ListElement(name, species, status, gender));

            }
            ListAdapter listAdapter = new ListAdapter(elements, this);

            RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(listAdapter);

            listAdapter.setOnclickListener(new  View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selection = elements.get(recyclerView.getChildAdapterPosition(view)).getName();
                    bundInfo.putString("name", selection);

                    Intent intent2 = new Intent(MainActivity.this, InfoActivity.class);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent2.putExtras(bundInfo);
                    startActivity(intent2);
                }
            });

        }catch (JSONException e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveDB(JSONObject jsnPersonaje) {

        ConexionSqliteHelper conn = new ConexionSqliteHelper(this, "db_character", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        try {
            values.put(Utils.FIELD_ID,jsnPersonaje.getInt("id"));
            values.put(Utils.FIELD_NAME,jsnPersonaje.getString("name"));
            values.put(Utils.FIELD_STATUS,jsnPersonaje.getString("status"));
            values.put(Utils.FIELD_SPECIES,jsnPersonaje.getString("species"));
            values.put(Utils.FIELD_TYPE,jsnPersonaje.getString("type"));
            values.put(Utils.FIELD_GENDER,jsnPersonaje.getString("gender"));
            values.put(Utils.FIELD_URL_IMAGE,jsnPersonaje.getString("image"));
            values.put(Utils.FIELD_URL_CHARACTER,jsnPersonaje.getString("url"));
            values.put(Utils.FIELD_CREATED,jsnPersonaje.getString("created"));
            values.put(Utils.FIELD_FAVORITE, 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Long result = db.insert(Utils.TABLE_CHARACTER, Utils.FIELD_ID, values);

        db.close();
    }

}