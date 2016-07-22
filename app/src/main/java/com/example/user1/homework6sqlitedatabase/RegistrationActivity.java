package com.example.user1.homework6sqlitedatabase;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user1.homework6sqlitedatabase.Database.DBUtils;
import com.example.user1.homework6sqlitedatabase.cmn.User;
import com.firebase.client.Firebase;

public class RegistrationActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPassword;
    Button btnRegister;
    DBUtils dbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        final Context context = getApplicationContext();

        Firebase.setAndroidContext(this);
        edtPassword = (EditText) findViewById(R.id.edt_password_register);
        edtUsername = (EditText) findViewById(R.id.edt_username_register);
        btnRegister = (Button) findViewById(R.id.btn_register_user);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbUtils = DBUtils.getInstance(context);
                dbUtils.writeUser(String.valueOf(edtUsername.getText()), String.valueOf(edtUsername.getText()));
                Firebase rootRef = new Firebase("https://homeworksqlite.firebaseio.com/");
                rootRef.push().setValue(new User(String.valueOf(edtUsername.getText()), String.valueOf(edtUsername.getText())));
            }
        });
    }
}
