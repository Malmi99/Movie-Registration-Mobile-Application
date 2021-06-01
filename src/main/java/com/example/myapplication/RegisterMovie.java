package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterMovie extends AppCompatActivity {

    EditText input1;
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
    }

    public void okButton(View view) {
        input1=findViewById(R.id.edt_name);
        String movieName = name.getText().toString();
        String movieYear = year.getText().toString();
        String movieDirector = director.getText().toString();
        String movieActors = actors.getText().toString();
        String movieRate = rate.getText().toString();
        String movieReview = review.getText().toString();


        Data data = new Data(movieName,movieYear,movieDirector,movieActors,movieRate,movieReview);
        dbHandler.addmoviesdetails(data,"add");


        Toast toast = Toast.makeText(this, "Successfully added ! ", Toast.LENGTH_SHORT);
        toast.show();
        name.setText("");
        year.setText("");
        director.setText("");
        actors.setText("");
        rate.setText("");
        review.setText("");


    }
}