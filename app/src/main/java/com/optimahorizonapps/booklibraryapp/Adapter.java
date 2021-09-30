package com.optimahorizonapps.booklibraryapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> book_id, book_title, book_author, book_pages;


    Adapter(Context context, ArrayList<String> book_id, ArrayList<String> book_title, ArrayList<String> book_author,
            ArrayList<String> book_pages) {
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bookId_textView.setText(String.valueOf(book_id.get(position)));
        holder.bookTitle_textView.setText(String.valueOf(book_title.get(position)));
        holder.bookAuthor_textView.setText(String.valueOf(book_author.get(position)));
        holder.bookPages_textView.setText(String.valueOf(book_pages.get(position)));
        holder.rowLayout.setOnClickListener(v -> {

            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id", book_id.get(position));
            intent.putExtra("title", book_title.get(position));
            intent.putExtra("author", book_author.get(position));
            intent.putExtra("pages", book_pages.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView bookId_textView, bookTitle_textView, bookAuthor_textView, bookPages_textView;
        private LinearLayoutCompat rowLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookId_textView = itemView.findViewById(R.id.bookId_textView);
            bookTitle_textView = itemView.findViewById(R.id.bookTitle_textView);
            bookAuthor_textView = itemView.findViewById(R.id.bookAuthor_textView);
            bookPages_textView = itemView.findViewById(R.id.bookPages_textView);
            rowLayout = itemView.findViewById(R.id.row_layout);
        }
    }
}
