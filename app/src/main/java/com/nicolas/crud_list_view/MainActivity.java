package com.nicolas.crud_list_view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseManager databaseManager;

    private EditText nameText;
    private EditText phoneText;

    private Button addButton;
    private Button contactsButton;
    private Button deleteAllButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        databaseManager = new DatabaseManager(this);
    }

    public void initComponents() {
        nameText = findViewById(R.id.name_text_view);
        phoneText = findViewById(R.id.number_text_view);

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = new Contact(nameText.getText().toString(), phoneText.getText().toString());
                databaseManager.insertContact(contact);
                cleanInputs();
            }
        });

        contactsButton = findViewById(R.id.contacts_button);
        contactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                startActivity(intent);
                cleanInputs();
            }
        });

        deleteAllButton = findViewById(R.id.delete_all_button);
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseManager.cleanData();
            }
        });
    }

    public void cleanInputs() {
        nameText.setText("");
        phoneText.setText("");
    }
}