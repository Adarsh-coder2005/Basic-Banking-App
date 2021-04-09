package com.adarsh.grip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button user,transact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (Button)findViewById(R.id.user);
        transact = (Button)findViewById(R.id.button3);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,second.class);
                startActivity(intent);
            }
        });

        transact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent last = new Intent(MainActivity.this,history.class);
                startActivity(last);
            }
        });
    }
}