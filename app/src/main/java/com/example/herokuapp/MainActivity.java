package com.example.herokuapp;

import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Game> gameList;
    private static CustomAdapter adapter;
    private Context context;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize fields
        context = this;
        listView = (ListView) findViewById(R.id.list_view);
        gameList = new ArrayList<>();
        adapter = new CustomAdapter(this, gameList);

        //set adapter
        listView.setAdapter(adapter);

        SharedPreferences preferences = getSharedPreferences("loginDetails", MODE_PRIVATE);
        username = preferences.getString("username", null);
        password = preferences.getString("password", null);

        //debug
        //Log.d("TO BE PASSED", username + " " + password);

        //TODO uncomment this once you have implemented getGamesRetro
        getGamesRetro(username, password);
    }

    private void getGamesRetro(String username, String password){

        //create the REST client
        GameClient client = ServiceGenerator.createService(GameClient.class, username, password);

        //TODO
        Call<List<Game>> call = client.games();

        call.enqueue(new Callback<List<Game>>() {

            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.isSuccess()) {
                    //Log.d("HTTP_GET_RESPONSE", response.raw().toString());
                    //TODO populate list with the respons (JSON)
                    for (Game singleGame : response.body()){
                        gameList.add(singleGame);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    // error response, no access to resource?
                    //Log.d("HTTP_GET_RESPONSE", response.raw().toString());
                    //TODO do something to say you got nothing back
                    Toast.makeText(getApplicationContext(), "Got nothing back...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                // something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
                //TODO do something to handle failure
                Toast.makeText(getApplicationContext(), "Failure occured...", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static void deleteGame(Game game){
        //TODO make a http request to delete the game
        adapter.notifyDataSetChanged();
    }

    /*
    private void testCase1(){
        //fetch backend stuffs using some s
        Gson gson = new GsonBuilder().create();
        Game games = gson.fromJson(readJSON(getResources(), R.raw.simple_json), Game.class);
        if(games == null){
            Toast.makeText(this, "DIDN'T WORK", Toast.LENGTH_SHORT).show();
        }else {
            gameList.add(games);
            adapter.notifyDataSetChanged();
        }

    }

    private void testCase2(){
        Gson gson = new GsonBuilder().create();
        Game[] games = gson.fromJson(readJSON(getResources(), R.raw.multi_json), Game[].class);
        if(games == null){
            Toast.makeText(this, "DIDN'T WORK", Toast.LENGTH_SHORT).show();
        }else {
            gameList.addAll(Arrays.asList(games));
            adapter.notifyDataSetChanged();
        }

    }
    */

    /**
     * Read from a resources file and create a object that will allow the creation of other
     * objects from this resource.
     *
     * @param resources An application {@link Resources} object.
     * @param id The id for the resource to load, typically held in the raw/ folder.
     */
    public String readJSON(Resources resources, int id) {
        InputStream inputStream = resources.openRawResource(id);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }
}
