package com.nicolas.crud_list_view;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "list_view_db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "contact";

    private static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, " +
            "number TEXT);"; // número não é REAL, é texto com traço e etc.

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertContact(Contact contact) {
        String name = contact.getName();
        String number = contact.getPhone();

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "INSERT INTO " + TABLE_NAME + " (name, number) VALUES (?, ?)";
        db.execSQL(sql, new Object[]{name, number});
        db.close();
    }

    public List<Contact> getAllContacts() {
        List<Contact> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT name, number FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(0);
                String telefone = cursor.getString(1);
                lista.add(new Contact(nome, telefone));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }

    public void deleteContactByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE name = ?", new Object[]{name});
        db.close();
    }

    public void cleanData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.close();
    }
}

