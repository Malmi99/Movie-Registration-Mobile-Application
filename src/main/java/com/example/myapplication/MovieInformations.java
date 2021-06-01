package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MovieInformations extends AppCompatActivity {

    EditText input1;
    String mid;
    private EditText name;
    private EditText year;
    private EditText director;
    private  EditText actors;
    private  EditText rate;
    private  EditText review;
    private Context context;
    private DbHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movie);

        name = findViewById(R.id.edt_name);
        year = findViewById(R.id.edt_year);
        director = findViewById(R.id.edt_director);
        actors = findViewById(R.id.edt_actors);
        rate = findViewById(R.id.edt_rate);
        review = findViewById(R.id.edt_review);

        context = this;
        dbHandler = new DbHandler(context);


        mid = getIntent().getExtras().getString("mid");

        Cursor c = dbHandler.getData(mid);
        c.moveToNext();

        name.setText(c.getString(1));
        year.setText(c.getString(2));
        director.setText(c.getString(3));
        actors.setText(c.getString(4));
        rate.setText(c.getString(5));
        review.setText(c.getString(6));
    }

    public void okButton(View view) {
        input1=findViewById(R.id.edt_name);
        String movieName = name.getText().toString().trim();;
        String movieYear = year.getText().toString().trim();;
        String movieDirector = director.getText().toString().trim();;
        String movieActors = actors.getText().toString().trim();;
        String movieRate = rate.getText().toString().trim();;
        String movieReview = review.getText().toString().trim();;



        Data data = new Data(movieName,movieYear,movieDirector,movieActors,movieRate,movieReview);
        dbHandler.addmoviesdetails(data,mid);

        finish();



    }
}