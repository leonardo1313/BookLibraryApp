package com.optimahorizonapps.booklibraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private EditText titleUpdate_editText, authorUpdate_editText, pagesUpdate_editText;
    private Button update_button;
    private String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        titleUpdate_editText = findViewById(R.id.update_title_editText);
        authorUpdate_editText = findViewById(R.id.update_author_editText);
        pagesUpdate_editText = findViewById(R.id.update_numOfPages_editText);
        update_button = findViewById(R.id.updateBook_button);

        //we need to cal getAndSetIntentData firs, and only then can we call updateData
        getAndSetIntentData();

        update_button.setOnClickListener(v -> {
            DatabaseHandler dbHandler = new DatabaseHandler(UpdateActivity.this);
            dbHandler.updateData(id, title, author, pages);
        });


    }

    public void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
            //Getting data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            //Setting Intent data
            titleUpdate_editText.setText(title);
            authorUpdate_editText.setText(author);
            pagesUpdate_editText.setText(pages);

        }else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}