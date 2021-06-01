package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class EditMovie extends AppCompatActivity {
    ListView listView ;   //declare variables , arraylists and objects

    DbHandler dbHandler;
    ArrayAdapter adapter;
    ArrayList<String> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        listView =findViewById(R.id.list_view);
        dbHandler=new DbHandler(this);
        list2=new ArrayList<>();

        displayname();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "position : " + position + " || id : " + id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MovieInformations.class);
                intent.putExtra("mid",Integer.toString(position+1));
                startActivity(intent);
                finish();
            }
        });


    }

    public void displayname() {
        Cursor cursor1 = dbHandler.getData();

        if (cursor1.getCount() == 0) {                    //check wheather database is empty or not
            Toast.makeText(this, "No data to display", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor1.moveToNext()){
               String starts="";
                String text="";

                String fav = cursor1.getString(7);
               int rating = Integer.parseInt(cursor1.getString(5));

                if(fav.equals("0")){
                    text += cursor1.getString(1)+ "           Favourite( - )";
                }else{
                    text += (cursor1.getString(1) + "         Favourite( ✔)");
                }

                if(rating>0){
                    text += "  |  ";
                   for(int i =0;i<10;i++){
                        if(i >= rating){
                            text += "★";
                       }
                        else{
                            text += "⭐";
                        }
                    }

                }

                list2.add(text);


            }
            adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,list2);   //create adapter and display updated list
            listView.setAdapter(adapter);//sorting list in alphabetical order
        }
    }


}