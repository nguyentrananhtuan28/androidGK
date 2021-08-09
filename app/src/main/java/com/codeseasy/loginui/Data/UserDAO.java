
package com.codeseasy.loginui.Data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.codeseasy.loginui.ClassModel.User;

import java.util.ArrayList;
import java.util.List;
public class UserDAO extends DBManager {

    public UserDAO(Context context) {
        super(context);
    }

    //Add new a user
    public boolean CheckLogin(String username,String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[]{USER_USER_NAME},
                USER_USER_NAME + "=?" + " and " + USER_PASS + "=?",
                new String[]{String.valueOf(username), String.valueOf(password)}, null, null, null, null);
        if (cursor.getCount()>0){
            return true;
        }
        return false;
    }
    public boolean addUser(User user) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(USER_USER_NAME, user.getUserName());
            values.put(USER_PASS, user.getPass());
            values.put(USER_NAME, user.getName());
            values.put(USER_STUDENT_ID, user.getStudenID());

            db.insert(TABLE_USER, null, values);
            db.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    /*
    Check a user Exits
     */
    public boolean CheckUserExits(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[]{USER_USER_NAME},
                USER_USER_NAME + "=?",
                new String[]{String.valueOf(username)}, null, null, null, null);
        if (cursor.getCount()>0){
            return true;
        }
        return false;
    }
//    /*
//    Select a user by Username
//     */
    public User getUserByUserName(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[]{USER_USER_NAME, USER_PASS, USER_NAME},
                USER_USER_NAME + "=?",
                new String[]{String.valueOf(username)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        cursor.close();
        db.close();
        return user;
    }
//
//    /*
//    Update password of user
//     */
    public int Update(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_PASS, user.getPass());
        return db.update(TABLE_USER, values, USER_USER_NAME + "=?", new String[]{String.valueOf(user.getUserName())});
    }
//     /*
//     Getting All User
//      */
//
    public List<User> getAllStudent() {
        List<User> listUser = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        // nhận dữ liệu từ câu query
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                user.setUserName(cursor.getString(0));
                user.setPass(cursor.getString(1));
                user.setName(cursor.getString(2));
                listUser.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listUser;
    }

//    /*
//    Delete a user by username
//     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, USER_NAME + " = ?",
                new String[]{String.valueOf(user.getUserName())});
        db.close();
    }
//
//    /*
//    Get Count User in Table Student
//     */
    public int getStudentsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }
}

