package com.adarsh.grip;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.adarsh.grip.dbhandler.mydb;
import com.adarsh.grip.models.transfers;
import com.adarsh.grip.models.user;

import java.util.ArrayList;
import java.util.List;

public class second extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mydb db = new mydb(second.this);

        /*transfers t = new transfers();
        t.setSender("Adarsh");
        t.setAmount("200");
        t.setReceiver("Ashmit");
        db.flow(t);
        Log.d("show","Details are: "+t.getSender()+" "+t.getReceiver());

        user user1 = new user();
        user1.setName("Adarsh");
        user1.setEmail("adarshnishen25@gmail.com");
        user1.setBalance("35000");
        db.add(user1);

        user user2 = new user();
        user2.setName("Ashmit");
        user2.setEmail("singhashmit67@gmail.com");
        user2.setBalance(("28000"));
        db.add(user2);

        user user3 = new user();
        user3.setName("Abuzar");
        user3.setEmail("321abuzar@gmail.com");
        user3.setBalance("30000");
        db.add(user3);*/

        /*user user4 = new user();
        user4.setName("Sarthak");
        user4.setEmail("sarthakdubey23@gmail.com");
        user4.setBalance("34500");
        db.add(user4);

        user user5 = new user();
        user5.setName("Alisha");
        user5.setEmail("bhardwaajalisha@gmail.com");
        user5.setBalance("20000");
        db.add(user5);

        user user6 = new user();
        user6.setName("Joy");
        user6.setEmail("joydey5634@gmail.com");
        user6.setBalance("17000");
        db.add(user6);

        user user7 = new user();
        user7.setName("Dennis");
        user7.setEmail("iamdennisidney@gmail.com");
        user7.setBalance("55000");
        db.add(user7);

        user user8 = new user();
        user8.setName("Ritik");
        user8.setEmail("mehraritik15@gmail.com");
        user8.setBalance("55000");
        db.add(user8);

        user user9 = new user();
        user9.setName("Heenal");
        user9.setEmail("keshwaniheenal@gmail.com");
        user9.setBalance("32160");
        db.add(user9);

        user user10 = new user();
        user10.setName("Nripesh");
        user10.setEmail("nripesharma2000@gmail.com");
        user10.setBalance("10000");
        db.add(user10);*/

        ArrayList<String> people = new ArrayList<>();
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent2 = new Intent(second.this,third.class);
                intent2.putExtra("item",i);
                startActivity(intent2);
            }
        });

        List<user> alluser = db.display();
        for (user users : alluser) {
            people.add("Name - \t"+users.getName() +"\n\n"+"Email - \t"+ users.getEmail() +"\n\n"+"Balance - \t"+ users.getBalance());
            Log.d("adarsh", "Name: " + users.getName() + " " + "email: " + users.getEmail());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, people);
        listView.setAdapter(arrayAdapter);
    }

}