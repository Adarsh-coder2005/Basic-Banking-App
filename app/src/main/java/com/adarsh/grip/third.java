package com.adarsh.grip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.adarsh.grip.dbhandler.mydb;
import com.adarsh.grip.models.user;

import java.util.List;

public class third extends AppCompatActivity {

        String[] item;
        ListView list;
        TextView t1,t2,t3,t4,t5,t6;
        Button b1,b2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_third);

            mydb db = new mydb(third.this);

            Intent intent3 = getIntent();
            int item = intent3.getIntExtra("item",0);

            List<user> allusers = db.display();

            t1 = findViewById(R.id.name);
            t2 = findViewById(R.id.user);
            t2.setText(allusers.get(item).getName());

            t3 = findViewById(R.id.email);
            t4 = findViewById(R.id.email_id);
            t4.setText(allusers.get(item).getEmail());

            t5 = findViewById(R.id.balance);
            t6 = findViewById(R.id.textView2);
            t6.setText(allusers.get(item).getBalance());

            b2 = findViewById(R.id.button2);
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent transfer = new Intent(third.this, transfer.class);
                    transfer.putExtra("name",allusers.get(item).getName());
                    transfer.putExtra("item",item);
                    startActivity(transfer);
                }
            });
        }
    }