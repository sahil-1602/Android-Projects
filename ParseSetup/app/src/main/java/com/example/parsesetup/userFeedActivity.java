package com.example.parsesetup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class userFeedActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    public class GetBitmapFromParse extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                Log.i("Async","successful");
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
//                final InputStream in = this.getContentResolver().openInputStream( uri );
                return BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        setTitle(username+"'s feed");

        linearLayout = findViewById(R.id.linearLayout);

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Image");

        query.whereEqualTo("username",username);
        query.orderByDescending("createdAt");




        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null && objects.size()>0){
                    for(ParseObject object : objects){
                        ParseFile file =(ParseFile) object.get("image");

                        String imageURL = "http://3.17.178.12/" + file.getUrl();
                        String correctedImageURL = imageURL.replace("undefined","parse");

                        GetBitmapFromParse getBitmapFromParse = new GetBitmapFromParse();
                        try {
                            Bitmap bitmap = getBitmapFromParse.execute(correctedImageURL).get();
                            ImageView imageView = new ImageView(getApplicationContext());
                            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT
                            ));
                            imageView.setImageBitmap(bitmap);
                            linearLayout.addView(imageView);
                            Log.i("Bitmap","Successful");


                        } catch (Exception i) {
                            i.printStackTrace();
                        }


//                        file.getDataInBackground(new GetDataCallback() {
//                            @Override
//                            public void done(byte[] data, ParseException e) {
//                                if(e==null && data!=null){
//                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
//                                    ImageView imageView = new ImageView(getApplicationContext());
//                                    imageView.setLayoutParams(new ViewGroup.LayoutParams(
//                                            ViewGroup.LayoutParams.MATCH_PARENT,
//                                            ViewGroup.LayoutParams.WRAP_CONTENT
//                                    ));
//
//                                    imageView.setImageBitmap(bitmap);
//
//                                    linearLayout.addView(imageView);
//
//                                }
//                            }
//                        });
                    }
                }
            }
        });



    }
}