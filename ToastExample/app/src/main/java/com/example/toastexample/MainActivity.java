package com.example.toastexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void toasting(View view){
        Log.i("Button","Yes it worked");
        TextView questionTextView = findViewById(R.id.questionTextView);
        EditText nameEditText = findViewById(R.id.nameEditText);
        Log.i("Name",nameEditText.getText().toString());
        Toast.makeText(this,"Hi "+nameEditText.getText().toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}