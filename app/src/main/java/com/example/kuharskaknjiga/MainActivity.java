package com.example.kuharskaknjiga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String url = "https://kricaj123.pythonanywhere.com/json";
    RecyclerView recyclerView;
    RecipeAdaptor adaptor;
    ArrayList<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptor = new RecipeAdaptor();
        recyclerView.setAdapter(adaptor);
        recipes = new ArrayList<>();
        getData();

    }

    private void getData() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for (int i = 0; i<response.length(); i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        Recipe recipe = new Recipe();

                        recipe.setImage("https://kricaj123.pythonanywhere.com/media/" + jsonObject.getString("rec_img"));
                        recipe.setTitle(jsonObject.getString("naslov"));
                        recipe.setIngredients("Ingredients: \n \n" + jsonObject.getString( "sestavine"));
                        recipe.setBody("Directions: \n \n" + jsonObject.getString("priprava"));
                        recipes.add(recipe);
                    }
                }
                catch (JSONException e){
                    Toast.makeText(MainActivity.this, "JSON is not valid",Toast.LENGTH_SHORT).show();
                }
                adaptor.setData(recipes);
                adaptor.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Error Occurred",Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
}
