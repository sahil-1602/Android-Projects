package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashSet;

import static com.example.notes.MainActivity.*;

public class Notes extends AppCompatActivity {
    int noteId;
    String conts;
    EditText notesEditText ;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        EditText editText = findViewById(R.id.titleEditTextView);
        notesEditText = findViewById(R.id.notesEditText);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId",-1);
        conts = intent.getStringExtra("content");
        if (noteId != -1) {
            editText.setText(MainActivity.notes.get(noteId));
            notesEditText.setText(conts);
        } else {
            MainActivity.notes.add("");
            noteId = MainActivity.notes.size() - 1;
            content.add("");

        }

        notesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                content.set(noteId, String.valueOf(charSequence));
//                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                HashSet<String> conts = new HashSet<>(content);
                sharedPreferences.edit().putStringSet("content", conts).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.notes.set(noteId, String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}