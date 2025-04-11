package com.nicolas.crud_list_view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactsActivity extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);

        databaseManager = new DatabaseManager(this);
        List<Contact> contacts = databaseManager.getAllContacts();

        recyclerView = findViewById(R.id.recycler_view);
        ContactAdapter adapter = new ContactAdapter(contacts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseManager = new DatabaseManager(this);
    }
}
