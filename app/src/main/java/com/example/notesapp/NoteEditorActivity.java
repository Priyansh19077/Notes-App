package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class NoteEditorActivity extends AppCompatActivity {
    EditText note;
    String new_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        note=findViewById(R.id.editor);
        Intent in=getIntent();
        final int position=in.getIntExtra("Position",-1);
        if(position!=-1) {
            note.setText(MainActivity.notes.get(position));
            new_string=MainActivity.notes.get(position);
            note.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    new_string=note.getText().toString();
                    MainActivity.notes.set(position,new_string);
                    MainActivity.adapter.notifyDataSetChanged();
                    SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);
                    HashSet<String> set=new HashSet<>(MainActivity.notes);
                    sharedPreferences.edit().putStringSet("notes",set).apply();
                }
                @Override
                public void afterTextChanged(Editable editable) {
                    new_string=note.getText().toString();
                    if(new_string.length()!=0) {
                        MainActivity.notes.set(MainActivity.notes.size() - 1, new_string);
                        MainActivity.adapter.notifyDataSetChanged();
                        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);
                        HashSet<String> set=new HashSet<>(MainActivity.notes);
                        sharedPreferences.edit().putStringSet("notes",set).apply();
                    }
                }
            });
        }
        else
        {
            new_string="";
            note.setText(new_string);
            new_string="Empty Note";
            MainActivity.notes.add(new_string);
            note.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    new_string=note.getText().toString();
                    if(new_string.length()!=0) {
                        MainActivity.notes.set(MainActivity.notes.size() - 1, new_string);
                        MainActivity.adapter.notifyDataSetChanged();
                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);
                        HashSet<String> set = new HashSet<>(MainActivity.notes);
                        sharedPreferences.edit().putStringSet("notes", set).apply();
                    }
                }
                @Override
                public void afterTextChanged(Editable editable) {
                    new_string=note.getText().toString();
                    if(new_string.length()!=0) {
                        MainActivity.notes.set(MainActivity.notes.size() - 1, new_string);
                        MainActivity.adapter.notifyDataSetChanged();
                        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);
                        HashSet<String> set = new HashSet<>(MainActivity.notes);
                        sharedPreferences.edit().putStringSet("notes", set).apply();
                    }
                }
            });
        }
    }
}