package io.github.chengscott.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final String pokemonTag = "Pokemon";
    public final String htcgTag = "HTCG";
    private Switch switchContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchContent = (Switch) findViewById(R.id.switchContent);
        switchContent.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppController.getInstance().getRequestQueue().cancelAll(htcgTag);
                    getPokemonObject();
                } else {
                    AppController.getInstance().getRequestQueue().cancelAll(pokemonTag);
                    getHTCGArray();
                }
            }
        });
        switchContent.setChecked(true);
    }

    private void getPokemonObject() {
        String url = "http://chengscott.tw/talk/page/Google_Volley_Intro/json/pokemon.json";
        JsonObjectRequest listItemRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<String> pokemons = new ArrayList<>();
                        for (int i = 1; i < response.length(); ++i) {
                            JSONObject pokemon;
                            try {
                                pokemon = response.getJSONObject(Integer.toString(i));
                                pokemons.add(pokemon.getString("name"));
                            } catch (Exception e) {

                            } finally {

                            }
                        }
                        PopulateListView(pokemons);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        AppController.getInstance().addToRequestQueue(listItemRequest, pokemonTag);
    }

    private void getHTCGArray() {
        String url = "http://chengscott.tw/talk/page/Google_Volley_Intro/json/htcg.json";
        JsonArrayRequest listItemRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<String> talks = new ArrayList<>();
                        for (int i = 0; i < response.length(); ++i) {
                            JSONObject talk;
                            try {
                                talk = (JSONObject) response.get(i);
                                talks.add(talk.getString("title"));
                            } catch (Exception e) {

                            } finally {

                            }
                        }
                        PopulateListView(talks);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        AppController.getInstance().addToRequestQueue(listItemRequest, htcgTag);
    }

    private void PopulateListView(List list) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                list);
        ((ListView) findViewById(R.id.listView)).setAdapter(adapter);
    }
}
