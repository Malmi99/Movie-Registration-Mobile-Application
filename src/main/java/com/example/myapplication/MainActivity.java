package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void addMovie(View view) {
        Intent intent = new Intent(MainActivity.this,RegisterMovie.class);
        startActivity(intent);
    }
    public void displayMovie(View view) {
        Intent intent = new Intent(MainActivity.this,DisplayMovie.class);
        startActivity(intent);
    }

    public void favourites(View view) {
        Intent intent = new Intent(MainActivity.this,Favourites.class);
        startActivity(intent);
    }
    public void EditMovie(View view) {
        Intent intent = new Intent(MainActivity.this,EditMovie.class);
        startActivity(intent);
    }

    public void SearchMovie(View view) {
        Intent intent = new Intent(MainActivity.this,Search_Movie.class);
        startActivity(intent);
    }

    public void rates(View view) {
        Intent intent = new Intent(MainActivity.this,Search_Movie.class);
        startActivity(intent);
    }
}