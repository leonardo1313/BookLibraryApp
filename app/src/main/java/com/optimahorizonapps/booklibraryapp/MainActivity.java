package com.optimahorizonapps.booklibraryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView book_RecyclerView;
    private FloatingActionButton addBook_Button;
    private DatabaseHandler dbHandler;
    private ArrayList<String> book_id, book_title, book_author, book_pages;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        book_RecyclerView = findViewById(R.id.book_recyclerView);
        addBook_Button = findViewById(R.id.add_floatingActionButton);

        addBook_Button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
            startActivity(intent);
        });

        dbHandler = new DatabaseHandler(MainActivity.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();

        storeDataInLists();

        adapter = new Adapter(MainActivity.this, this, book_id, book_title, book_author, book_pages);
        book_RecyclerView.setAdapter(adapter);
        book_RecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            recreate();
        }
    }

    public void storeDataInLists() {
        Cursor cursor = dbHandler.readAllData();
        
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data to display.", Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()) {
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDeleteAllDialog();

        }
        return super.onOptionsItemSelected(item);
    }

    public void confirmDeleteAllDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Delete All Books");
        alertDialog.setMessage("Are you sure you want to delete all Books?");
        alertDialog.setPositiveButton("Yes", (dialog, which) -> {
            DatabaseHandler dbHandler = new DatabaseHandler(this);
            dbHandler.deleteAllBooks();
            //refresh Activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        alertDialog.setNegativeButton("No", (dialog, which) -> {

        });
        alertDialog.create().show();
    }
}