package com.codeseasy.loginui.Data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.codeseasy.loginui.ClassModel.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDAO extends DBManager{

    public BookDAO(Context context) {
        super(context);
    }
//    //Add new a Book
    public void addBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BOOK_NAME, book.getBookName());
        values.put(BOOK_IMAGE, book.getImage());
        values.put(BOOK_PRICE, book.getPrice());

        db.insert(TABLE_BOOK,null,values);
        db.close();
    }
//    /*
//    Select a Book by Book
//     */
    public Book getBookByID(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BOOK, new String[] { BOOK_ID, BOOK_NAME, BOOK_IMAGE, BOOK_PRICE},
                BOOK_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Book book = new Book(cursor.getInt(0),cursor.getString(1),cursor.getBlob(2),cursor.getInt(3));
        cursor.close();
        db.close();
        return book;
    }
//    /*
//    Update Book
//     */
    public int Update(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BOOK_NAME, book.getBookName());
        values.put(BOOK_PRICE, book.getPrice());
        values.put(BOOK_IMAGE, book.getImage());
        return db.update(TABLE_BOOK,values,BOOK_ID +"=?",new String[] { String.valueOf(book.getId())});
    }
//     /*
//     Getting All User
//      */
//
    public List<Book> getAllBook() {
        List<Book> listUser = new ArrayList<Book>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BOOK;

        SQLiteDatabase db = this.getWritableDatabase();
//        // nhận dữ liệu từ câu query
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Book book = new Book(cursor.getInt(0), cursor.getString(1), cursor.getBlob(2), cursor.getInt(3));
                book.setId(cursor.getInt(0));
                book.setBookName(cursor.getString(1));
                book.setImage(cursor.getBlob(2));
                book.setPrice(cursor.getInt(3));
                listUser.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listUser;
    }
//    /*
//    Delete a Book by id
//     */
    public void deleteBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOK, BOOK_ID + " = ?",
                new String[] { String.valueOf(book.getId()) });
        db.close();
    }
    //Get Count User in Table Student
    public int getBookCount() {
        String countQuery = "SELECT  * FROM " + TABLE_BOOK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }
}

