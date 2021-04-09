package com.adarsh.grip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.adarsh.grip.dbhandler.mydb;
import com.adarsh.grip.models.transfers;

import java.util.ArrayList;
import java.util.List;

public class history extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mydb db = new mydb(history.this);

        ArrayList<String> hist = new ArrayList<>();
        listView = findViewById(R.id.hist);

        List<transfers> allhist = db.details();
        for(transfers tran:allhist){
            hist.add("Sender - \t"+tran.getSender()+"\n\n"+"Transferred Amount - \t"+tran.getAmount()+"\n\n"+"Receiver - \t"+tran.getReceiver());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,hist);
        listView.setAdapter(arrayAdapter);
    }
}