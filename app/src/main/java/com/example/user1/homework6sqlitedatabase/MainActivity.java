package com.example.user1.homework6sqlitedatabase;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user1.homework6sqlitedatabase.Database.DBHelper;
import com.example.user1.homework6sqlitedatabase.Database.DBUtils;

public class MainActivity extends AppCompatActivity {

    EditText edtName;
    EditText edtPass;
    Button btnLogin;
    Button btnRegister;
    DBUtils dbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = getApplicationContext();
        edtName = (EditText) findViewById(R.id.edt_username);
        edtPass = (EditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbUtils = DBUtils.getInstance(context);
                Cursor cursor = dbUtils.readUsers();
                if(cursor.moveToFirst()){
                    do {
                        String username = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_USERNAME));
                        String password = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PASSWORD));
                        if (String.valueOf(edtName.getText()).equals(username)
                                && String.valueOf(edtPass.getText()).equals(password)) {
                            startActivity(new Intent(MainActivity.this, LoggedInActivity.class));
                            break;
                        }
                    } while (cursor.moveToNext());
                }
                Toast.makeText(context,"No user found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
