package com.example.parsesetup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseSession;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    TextView loginTextView;
    Boolean signUpIsActive = true;
    EditText passwordEditText;
    EditText userNameEditText;

    public void showUserList(){
        Intent intent = new Intent(getApplicationContext(),UserListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if(i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == keyEvent.ACTION_DOWN){
            SignUpClicked(view);
        }

        return false;
    }

    @Override
    public void onClick(View view) {

        Button signUpButton = findViewById(R.id.signUpButton);

        if(view.getId() == R.id.logIntextView){
            if(signUpIsActive){
                signUpIsActive=false;
                signUpButton.setText("LogIn");
                loginTextView.setText("Or, Sign Up");
            }else{
                signUpIsActive=true;
                signUpButton.setText("Sign Up");
                loginTextView.setText("Or, Log In");
            }
        }else if(view.getId() == R.id.backgroundLayout || view.getId() == R.id.imageView){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }

    }

    public  void SignUpClicked(View view){

        if(userNameEditText.getText().toString().matches("")  || passwordEditText.getText().toString().matches("")){
            Toast.makeText(this, "A username and Password required", Toast.LENGTH_SHORT).show();
        }else{
            if(signUpIsActive) {
                ParseUser user = new ParseUser();
                user.setUsername(userNameEditText.getText().toString());
                user.setPassword(passwordEditText.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.i("SignUp", "Successful");
                            showUserList();
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else{
                ParseUser.logInInBackground(userNameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null){
                            Log.i("Log In","Successful");
                            showUserList();
                        }else{
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Instagram");

        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("myappID")
                .clientKey("1JTeDim0jcx2")
                .server("http://18.224.62.18/parse/")
                .build()
        );




        loginTextView = findViewById(R.id.logIntextView);
        userNameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEdittext);
        ImageView logoImageView = findViewById(R.id.imageView);
//        RelativeLayout backgroundLayout = findViewById(R.id.backgroundLayout);

        loginTextView.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){
            showUserList();
        }

        ParseUser user = new ParseUser();
        user.setUsername("nick");
        user.setPassword("123123");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    //OK
                    Log.i("Sign -up","Success");
                }
            }
        });

        ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);


    }
}