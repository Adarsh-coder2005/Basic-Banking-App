package com.adarsh.grip.dbhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.adarsh.grip.models.transfers;
import com.adarsh.grip.models.user;
import com.adarsh.grip.params.params;

import java.util.ArrayList;
import java.util.List;

public class mydb extends SQLiteOpenHelper {

    public static final String TABLE3 = "records";

    public mydb(Context context) {
        super(context, params.DB_NAME, null, params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + params.TABLE1_NAME + "(" +
                params.USER_NAME + " TEXT PRIMARY KEY, " + params.EMAIL + " TEXT, "
                + params.BALANCE + " TEXT)";
        String str = "CREATE TABLE " + params.TABLE + "(" +
                params.SENDER + " TEXT, " + params.AMOUNT + " TEXT, " + params.RECEIVER
                + " TEXT)";

        db.execSQL(create);
        db.execSQL(str);

        Log.d("adarsh", "Table 2 is created" + str);
        Log.d("adarsh", "Query is running " + create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void add(user u) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(params.USER_NAME, u.getName());
        values.put(params.EMAIL, u.getEmail());
        values.put(params.BALANCE, u.getBalance());

        db.insert(params.TABLE1_NAME, null, values);
        Log.d("adarsh", "Successfully inserted");

        db.close();
    }

    public void flow(transfers money) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(params.AMOUNT, money.getAmount());
        values.put(params.RECEIVER, money.getReceiver());
        values.put(params.SENDER, money.getSender());
        db.insert(params.TABLE,null,values);
        db.close();
    }

    public List<user> display() {
        List<user> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "Select * From " + params.TABLE1_NAME;
        Cursor cursor = db.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                user person = new user();
                person.setName(cursor.getString(0));
                person.setEmail(cursor.getString(1));
                person.setBalance(cursor.getString(2));
                userList.add(person);
            } while (cursor.moveToNext());
        }
        return userList;
    }

    public List<transfers> details() {
        List<transfers> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String insert = "Select * From " + params.TABLE;
        Cursor cursors = db.rawQuery(insert, null);

        if (cursors.moveToFirst()) {
            do {
                transfers fund = new transfers();
                fund.setSender(cursors.getString(0));
                fund.setAmount(cursors.getString(1));
                fund.setReceiver(cursors.getString(2));
                list.add(fund);
            } while (cursors.moveToNext());
        }
        return list;
    }

    public int update(user users, transfers trans) {
        SQLiteDatabase db = this.getWritableDatabase();

        int diff = Integer.parseInt(users.getBalance())-Integer.parseInt(trans.getAmount());
        String minus = String.valueOf(diff);
        ContentValues value = new ContentValues();
        value.put(params.BALANCE, minus);

        return db.update(params.TABLE1_NAME, value, params.USER_NAME + "=?",
                new String[]{String.valueOf(trans.getSender())});
    }

    public int update1(user users, transfers trans) {
        SQLiteDatabase db = this.getWritableDatabase();

        int sum =  Integer.parseInt(users.getBalance())+Integer.parseInt(trans.getAmount());
        String plus = String.valueOf(sum);
        ContentValues value = new ContentValues();
        value.put(params.BALANCE, plus);

        return db.update(params.TABLE1_NAME, value, params.USER_NAME + "=?",
                new String[]{String.valueOf(trans.getReceiver())});
    }

    public void delete(String trans){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(params.TABLE,params.RECEIVER+"=?",new String[]{String.valueOf(trans)});
        db.close();
    }
}
