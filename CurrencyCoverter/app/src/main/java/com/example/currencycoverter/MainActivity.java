package com.example.currencycoverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convert(View view){
        Log.i("Info","Button pressed");
        EditText currencyEditText = (EditText)findViewById(R.id.currencyEditText);
        String string = currencyEditText.getText().toString();
        Double currency = Double.valueOf(string).doubleValue();
        Toast.makeText(this,currency+" is equal to " + "$"+ currency*0.0131968, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}