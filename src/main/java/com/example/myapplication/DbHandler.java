package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "movie";
    private static final int VERSION = 9;
    private static final String TABLE_NAME = "movie_table";

    //Column names
    private static final String ID = "id";
    public static final String MOVIE_NAME = "name";
    public static final String MOVIE_YEAR = "year";
    public static final String MOVIE_DIRECTOR = "director";
    public static final String MOVIE_ACTOR = "actor";
    public static final String MOVIE_RATE = "rate";
    public static final String MOVIE_REVIEW = "review";
    public static final String FAVOUR = "favourites";


    private static String[] FROM = {ID, MOVIE_NAME, MOVIE_YEAR, MOVIE_DIRECTOR, MOVIE_ACTOR, MOVIE_RATE, MOVIE_REVIEW, FAVOUR};
    private static String ORDER_BY = ID + " DESC ";

    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//create tables
        String SQL_CREATE_TABLES = "CREATE TABLE " + DbHandler.TABLE_NAME + " ("
                + DbHandler.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DbHandler.MOVIE_NAME + " TEXT,"
                + DbHandler.MOVIE_YEAR + " INTEGER,"
                + DbHandler.MOVIE_DIRECTOR + " TEXT,"
                + DbHandler.MOVIE_ACTOR + " TEXT,"
                + DbHandler.MOVIE_RATE + " INTEGER,"
                + DbHandler.MOVIE_REVIEW + " TEXT,"
                + DbHandler.FAVOUR + " INTEGER)";


        db.execSQL(SQL_CREATE_TABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        //drop older table if existed
        db.execSQL(DROP_TABLE_QUERY);
        //Create tables again
        onCreate(db);
    }

    public void addMovieData(Data data) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();//structuring data and saved data in db
        contentValues.put(MOVIE_NAME, data.getName());
        contentValues.put(MOVIE_YEAR, data.getYear());
        contentValues.put(MOVIE_DIRECTOR, data.getDirector());
        contentValues.put(MOVIE_ACTOR, data.getActors());
        contentValues.put(MOVIE_RATE, data.getRate());
        contentValues.put(MOVIE_REVIEW, data.getReview());
        contentValues.put(FAVOUR, 0);


        //save to table
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        //sqLiteDatabase.close();
    }

    public Cursor getData() {                                             //to get phrases
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;

    }
    public Cursor getData(String id){              //to get details
        SQLiteDatabase sqLitedb =this.getReadableDatabase();
        System.out.println("ID is : " + id);
        String query="SELECT * FROM " +TABLE_NAME + " WHERE id=" + id;
        Cursor cursor=sqLitedb.rawQuery(query,null);
        return cursor;

    }


    public String update(Data data) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MOVIE_NAME, data.getName());
        values.put(MOVIE_YEAR, data.getYear());
        values.put(MOVIE_DIRECTOR, data.getDirector());
        values.put(MOVIE_ACTOR, data.getActors());
        values.put(MOVIE_RATE, data.getRate());
        values.put(MOVIE_REVIEW, data.getReview());
        String update = String.valueOf(database.update(TABLE_NAME, values, MOVIE_NAME + "=?", new String[]{data.getName()}));
        database.close();
        return update;

    }

    public Cursor getMovieId(String word) {                           //to get id of phrase
        SQLiteDatabase database = this.getWritableDatabase();
        String query = " SELECT " + ID + " FROM " + TABLE_NAME + " WHERE " + MOVIE_NAME + " = '" + word + "'";
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }

    public void update(String newWord, int id, String word) {               //update phrases
        SQLiteDatabase database = this.getWritableDatabase();
        String q = " UPDATE " + TABLE_NAME + " SET " + MOVIE_NAME + " = '" + newWord + "' WHERE " + ID + " = '" + id +
                "' " + " AND " + MOVIE_NAME + " = '" + word + "'";
        database.execSQL(q);
    }
    public void addmoviesdetails(Data data,String type) {   //to add details
        SQLiteDatabase sqLitedb = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MOVIE_NAME, data.getName());
        values.put(MOVIE_YEAR, data.getYear());
        values.put(MOVIE_DIRECTOR, data.getDirector());
        values.put(MOVIE_ACTOR, data.getActors());
        values.put(MOVIE_RATE, data.getRate());
        values.put(MOVIE_REVIEW, data.getReview());

        if(type.equals("add")){
            values.put(FAVOUR,0);
            sqLitedb.insert(TABLE_NAME,null,values);
        }
        else{
            sqLitedb.update(TABLE_NAME, values, "id="+type,null);
        }

    }

    public boolean updateFavourites(String movie_name, int value) {                           //to get id of phrase
        try{
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + FAVOUR + " = " + value + " WHERE " + MOVIE_NAME + " = '" + movie_name + "'";
        database.execSQL(query);
        return true;
        }catch(Exception ex){
            return false;
        }

    }
    public List getFavourites(){      //to get details
        SQLiteDatabase sqLitedb =this.getReadableDatabase();

        String query="SELECT * FROM " + TABLE_NAME + " WHERE " + FAVOUR + " = 1";
        Cursor cursor=sqLitedb.rawQuery(query,null);
        HashMap<String,Integer> map = new HashMap<>();
        ArrayList objList = new ArrayList<>();
        ArrayList<String> list = new ArrayList<String>();

        while(cursor.moveToNext()){
            list.add(cursor.getString(1));
            map.put(cursor.getString(1),cursor.getInt(7));
        }

        objList.add(list);
        objList.add(map);
        return objList;
}

    public Cursor search(String searchWord){
        SQLiteDatabase sqLitedb = this.getWritableDatabase();
        String searchQue="SELECT * FROM " +TABLE_NAME+
                " WHERE ("+MOVIE_NAME+" LIKE '%"+searchWord+"%' OR year LIKE '%"+searchWord+"%' OR director LIKE '%"+searchWord+"%' OR "+MOVIE_ACTOR+" LIKE '%"+searchWord+"%' OR rate LIKE '%"+searchWord+"%' OR review LIKE '%"+searchWord+"%')";
        Cursor cursor=sqLitedb.rawQuery(searchQue,null);
        return cursor;
    }



}

