package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Search_Movie extends AppCompatActivity {
    Button btn;
    EditText editText;
    ListView Result_View;
   DbHandler dbHandler;
    ArrayList<String> arrayList;
    String item;



    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__movie);

        // initializing objects
        btn = findViewById(R.id.lookup_btn);
        editText = findViewById(R.id.search_editText);
        Result_View = findViewById(R.id.listview);
        dbHandler = new DbHandler(this);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDetails(Result_View);
            }
        });
    }

    public void searchDetails(View view) {
        item = editText.getText().toString();
        dbHandler = new DbHandler(this);
        Cursor cursor = dbHandler.search(item);
        arrayList = new ArrayList<String>();
        while (cursor.moveToNext()) {
            String moviesName = cursor.getString(1);
            int moviesYear = cursor.getInt(2);
            String moviesDirector = cursor.getString(3);
            String moviesActors = cursor.getString(4);
            int moviesRating = cursor.getInt(5);
            String moviesReview = cursor.getString(6);

            StringBuilder stringBuiler = new StringBuilder();
            stringBuiler.append(moviesName + " " + moviesYear + " " + moviesDirector + " " + moviesActors + " " + moviesRating + " " + moviesReview + " ");
            String result = stringBuiler.toString();
            arrayList.add(result);


        }


        if (arrayList.size() == 0) {
            Toast.makeText(this, "No data to display", Toast.LENGTH_SHORT).show();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Search_Movie.this, android.R.layout.simple_list_item_1, arrayList);
        Result_View.setAdapter(adapter);

    }




}