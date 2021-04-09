package com.adarsh.grip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.adarsh.grip.dbhandler.mydb;
import com.adarsh.grip.models.transfers;
import com.adarsh.grip.models.user;

import java.util.List;

public class transfer extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView t1;
    EditText t2;
    Button send,cancel;
    String[] names = {"Select","Adarsh","Ashmit","Abuzar","Sarthak","Alisha","Joy","Dennis","Heenal","Nripesh","Ritik"};
    Spinner spin;
    String bal;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        mydb db = new mydb(transfer.this);
        transfers tran1 = new transfers();

        Intent intent4 = getIntent();
        int item = intent4.getIntExtra("item",0);
        final String[] name = {intent4.getStringExtra("name")};

        List<user> allusers = db.display();

        t1 = findViewById(R.id.textView2);
        t1.setText(allusers.get(item).getBalance());

        t2 = findViewById(R.id.inr);

        spin = findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,names);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(count==1){
                    allusers.get(i).setBalance(allusers.get(i).getBalance()+"-"+tran1.getAmount());
                    db.update1(allusers.get(i),tran1);
                    count = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 1;
                tran1.setSender(name[0]);
                tran1.setAmount(t2.getText().toString());
                tran1.setReceiver(spin.getSelectedItem().toString());
                db.flow(tran1);
                Toast.makeText(getApplicationContext(),"Sent Successfully",Toast.LENGTH_SHORT).show();
                db.update(allusers.get(item),tran1);
                // db.update1(tran1.getReceiver(),tran1);
            }
        });

        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                t2.setText("INR");
                Toast.makeText(getApplicationContext(),"Transaction Cancelled",Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}