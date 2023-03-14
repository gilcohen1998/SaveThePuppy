package com.example.savethepuppy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class InfoActivity extends AppCompatActivity {

    ImageView exit_info, facebook, instagram, youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Getting all the ids of all the needed

        exit_info = findViewById(R.id.exit_info);
        facebook = findViewById(R.id.facebook_icon);
        instagram = findViewById(R.id.instagram_icon);
        youtube = findViewById(R.id.youtube_icon);

        CustomScrollView outerScrollView = findViewById(R.id.outerScrollView);
        ScrollView innerScrollView = findViewById(R.id.innerScrollView);
        ScrollView innerScrollView2 = findViewById(R.id.innerScrollView2);
        ScrollView innerScrollView3 = findViewById(R.id.innerScrollView3);
        outerScrollView.setChildScrollView(innerScrollView);
        outerScrollView.setChildScrollView(innerScrollView2);
        outerScrollView.setChildScrollView(innerScrollView3);

        // Exit button

        exit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish(); // Finishing the current activity
            }
        });

        // Facebook icon (moving into my facebook page)

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/profile.php?id=100001783674694";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        // Instagram icon (moving into my instagram page)

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.instagram.com/gilcohen55/?hl=en";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        // Youtube icon (moving into my youtube page)

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/@gilcohen8324/featured";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });


    }
}

