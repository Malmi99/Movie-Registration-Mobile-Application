package com.example.myapplication;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DisplayMovie extends AppCompatActivity {

    ListView listWords;
    Button favouriteButton;
    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;
    DbHandler dbHandler;
    TextView count2;
    HashMap<String,Integer> map = new HashMap<String, Integer>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie);
        listWords=findViewById(R.id.list_view);
        dbHandler=new DbHandler(this);//initializing database object
        arrayList=new ArrayList<>();
        favouriteButton = findViewById(R.id.favourites_button);
        arrayList=new ArrayList<>();
        dbHandler = new DbHandler(this);

        favouriteButton.setOnClickListener(new View.OnClickListener() {
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

        showData();   //display saved data

    }
    public void showData(){


        Cursor cs=dbHandler.getData();                      //call getData method to retrieve data from database
        while(cs.moveToNext()){
            arrayList.add(cs.getString(1));
            map.put(cs.getString(1),cs.getInt(6));
        }

        if (cs.getCount()==0){                    //check wheather database is empty or not
            Toast.makeText(this,"No data to display",Toast.LENGTH_SHORT).show();
        }else {
            Collections.sort(arrayList);                         //sorting list in alphabetical order

            adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice,arrayList);   //create adapter and display updated list
            listWords.setAdapter(adapter);

            for(int i = 0;i < arrayList.size();i++){
                if(map.get(arrayList.get(i)) == 1){
                    listWords.setItemChecked(i,true);
                }
            }//Checking items which are already in Favourites

        }
    }




}
