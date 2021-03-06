package com.example.myapplication;

public class Data {
    private int id;
    private String name;
    private String year;
    private String director;
    private String actors;
    private String rate;
    private String review;



    public Data(String name, String year, String director, String actors,String rate,String review) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.actors = actors;
        this.rate = rate;
        this.review = review;

    }

    public Data(int id, String name,  String year, String director,String actors,String rate,String review) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.director = director;
        this.actors = actors;
        this.rate = rate;
        this.review = review;

    }

    public Data(String name, int year, String director, String actors, int rating, String review) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.rate = review;
    }}


