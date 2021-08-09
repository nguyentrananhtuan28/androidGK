package com.codeseasy.loginui.Data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="bookdb";
    protected static final String TABLE_USER ="users";
    protected static final String USER_USER_NAME ="usename";
    protected static final String USER_NAME ="name";
    protected static final String USER_PASS ="pass";
    protected static final String USER_STUDENT_ID ="studentid";

    protected static final String TABLE_BOOK ="books";
    protected static final String BOOK_ID ="id";
    protected static final String BOOK_NAME ="bookname";
    protected static final String BOOK_IMAGE ="image";
    protected static final String BOOK_PRICE = "price";
    protected static final boolean IS_FIRST = false ;

    protected Context context;

    public DBManager(Context context) {
        super(context, DATABASE_NAME,null, 1);
        Log.d("DBManager", "DBManager: ");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table user
        String sqlQuery = "CREATE TABLE "+TABLE_USER +" (" +
                USER_USER_NAME +" TEXT primary key, "+
                USER_PASS + " TEXT, "+
                USER_NAME + " TEXT, "+
                USER_STUDENT_ID +" TEXT)";
        db.execSQL(sqlQuery);
        //create table BOOK
        sqlQuery = "CREATE TABLE "+TABLE_BOOK +" (" +
                BOOK_ID +" integer  primary key, "+
                BOOK_NAME + " TEXT, "+
                BOOK_IMAGE + " BLOB, "+
                BOOK_PRICE +" integer)";
        db.execSQL(sqlQuery);
        Toast.makeText(context, "Create successfylly", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        onCreate(db);
        Toast.makeText(context, "Drop successfylly", Toast.LENGTH_SHORT).show();
    }

}
