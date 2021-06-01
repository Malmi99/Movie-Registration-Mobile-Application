package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class Favourites extends AppCompatActivity {
    Button button_fav;
    ListView listWords;
    DbHandler dbHandler;
    ArrayAdapter arrayAdapter;
    HashMap<String,Integer> map;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        button_fav=findViewById(R.id.button_favourites);
        listWords = findViewById(R.id.listName);
        dbHandler = new DbHandler(this);
        arrayList = (ArrayList<String>)dbHandler.getFavourites().get(0);
        map = (HashMap<String,Integer>)dbHandler.getFavourites().get(1);


        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice,arrayList);
        listWords.setAdapter(arrayAdapter);

        for(int i = 0;i < arrayList.size();i++){
            if(map.get(arrayList.get(i)) == 1){
                listWords.setItemChecked(i,true);
            }
        }



        button_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int count = listWords.getCount();
                for(int i = 0;i<count ; i++){
                    if(listWords.isItemChecked(i)){
                        dbHandler.updateFavourites(listWords.getItemAtPosition(i).toString(),1);
                    }else{
                        dbHandler.updateFavourites(listWords.getItemAtPosition(i).toString(),0);
                    }
                }
            }

        });
    }
}