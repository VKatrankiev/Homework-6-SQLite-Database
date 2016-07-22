package com.example.user1.homework6sqlitedatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user1.homework6sqlitedatabase.Database.DBHelper;
import com.example.user1.homework6sqlitedatabase.Database.DBUtils;
import com.example.user1.homework6sqlitedatabase.cmn.User;

import java.util.ArrayList;

public class LoggedInActivity extends AppCompatActivity {

    TextView txtUsers;
    DBUtils dbUtils;
    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        txtUsers = (TextView) findViewById(R.id.txt_users);
        dbUtils = DBUtils.getInstance(this);
        users = new ArrayList<>();
        readDB();
        String tempText = "";
        for (User user : users) {
            tempText += "name: " + user.getUsername() + " password: " + user.getPassword() + "\n";
        }
        txtUsers.setText(tempText);

    }

    private void readDB() {
        Cursor cursor = dbUtils.readUsers();
        if (cursor.moveToFirst()) {
            do {
                users.add(new User(cursor.getString(cursor.getColumnIndex(DBHelper.KEY_USERNAME)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PASSWORD))));
            } while (cursor.moveToNext());
        }
    }
}
