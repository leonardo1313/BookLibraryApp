package com.optimahorizonapps.booklibraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBookActivity extends AppCompatActivity {

    private EditText title_editText, author_editText, pages_editText;
    private Button saveBook_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_editText = findViewById(R.id.title_editText);
        author_editText = findViewById(R.id.author_editText);
        pages_editText = findViewById(R.id.numOfPages_editText);
        saveBook_button = findViewById(R.id.saveBook_button);

        saveBook_button.setOnClickListener(v -> {
            DatabaseHandler handler = new DatabaseHandler(AddBookActivity.this);
            handler.addBook(title_editText.getText().toString().trim(),
                            author_editText.getText().toString().trim(),
                            Integer.parseInt(pages_editText.getText().toString().trim()));
        });
    }
}