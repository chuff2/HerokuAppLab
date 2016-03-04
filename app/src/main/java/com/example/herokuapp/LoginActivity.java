package com.example.herokuapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = (EditText) findViewById(R.id.username_entry);
        final EditText password = (EditText) findViewById(R.id.password_entry);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //shared preference here
                SharedPreferences loginDetails = getSharedPreferences("loginDetails", MODE_PRIVATE);
                loginDetails.edit()
                        .putString("username", username.getText().toString())
                        .putString("password", password.getText().toString())
                        .apply();
                Log.d("USERNAME AND PASSWORD", username.getText().toString() + " " + password.getText().toString());
                startMainActivity();
            }
        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startMainActivity();
                return false;
            }
        });
    }

    private void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
