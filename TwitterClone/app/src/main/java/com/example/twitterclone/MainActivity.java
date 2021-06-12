package com.example.twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText usernameEditText;
    EditText passwordEditText;
    Button signInButton;
    TextView logInTextView;
    boolean signInActive = true;

    public void showList(){
        Intent intent = new Intent(getApplicationContext(),UserListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("myappID")
                .clientKey(null)
                .server("http://18.224.62.18:1337/parse/")
                .build()
        );



        usernameEditText = findViewById(R.id.userNameEdittextView);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton = findViewById(R.id.signInButton);
        logInTextView = findViewById(R.id.logInTextView);

        logInTextView.setOnClickListener(this);

//        ParseUser user = new ParseUser();
//        user.setUsername("nick");
//        user.setPassword("123123");
//
//        user.signUpInBackground(new SignUpCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e==null){
//                    //OK
//                    Log.i("Sign -up","Success");
//                }
//            }
//        });

        ParseObject object = new ParseObject("Example Class");
        object.put("My Number",123);
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    Log.i("Classes added","successfully");
                }else{
                    e.printStackTrace();
                }
            }
        });

//        ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);


    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.logInTextView){
            if(signInActive) {
                signInActive = false;
                signInButton.setText("Log In");
                logInTextView.setText("Or, Sign In");
            }else{
                signInActive = true;
                signInButton.setText("Sign In");
                logInTextView.setText("Or, Log In");
            }
        }
    }

    public void signInClicked(View view){

        if(usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")){
            Toast.makeText(this, "A username and password is required!", Toast.LENGTH_SHORT).show();
        }else{
            if(signInActive){
                //sign up
                ParseUser user = new ParseUser();
                user.setUsername(usernameEditText.getText().toString());
                user.setPassword(passwordEditText.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Log.i("Sign In","SUCCESSFUL");
                            showList();
                        }else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }else{
                //log in
                ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null){
                            Log.i("Log In","SUCCESSFUL");
                        }else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }



    }
}