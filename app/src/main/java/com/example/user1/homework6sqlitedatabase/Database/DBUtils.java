package com.example.user1.homework6sqlitedatabase.Database;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by user1 on 23.7.2016 г..
 */
public class DBUtils {
    private static DBUtils instance;
    private DBHelper db;

    private DBUtils (Context context){
        initDB(context);
    }

    private DBHelper initDB(Context context) {
        if(db == null){
            db = new DBHelper(context);
        }
        return db;
    }

    public static DBUtils getInstance(Context context){
        if(instance == null){
            instance = new DBUtils(context);
        }
        return instance;
    }

    public void writeUser(String bitmap, String pass){
        db.insertUser(bitmap, pass);
    }
    public Cursor readUsers(){
        return db.getUser();
    }
}
