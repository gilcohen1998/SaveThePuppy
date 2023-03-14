package com.example.savethepuppy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Middleshop_Activity extends AppCompatActivity {

    Button skin_shop, music_shop, spike_shop, chest_shop;
    ImageView exit_to_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Find the ids of all the needed

        setContentView(R.layout.activity_middle_shop);
        skin_shop = findViewById(R.id.skinshop_button);
        music_shop = findViewById(R.id.musicshop_button);
        spike_shop = findViewById(R.id.spikeshop_button);
        chest_shop = findViewById(R.id.chestshop_button);
        exit_to_main = findViewById(R.id.exitmiddleshop_button);

        chest_shop.setOnClickListener(new View.OnClickListener() { // The chest shop button
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Middleshop_Activity.this, Chestshop_Activity.class);
                startActivity(intent); // Moving into chest shop activity
                overridePendingTransition(0, 0);
                finish();
            }
        });

        skin_shop.setOnClickListener(new View.OnClickListener() { // The skin shop button
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Middleshop_Activity.this, ShopActivity.class);
                startActivity(intent); // Moving into skin shop activity
                overridePendingTransition(0, 0);
                finish();
            }
        });

        spike_shop.setOnClickListener(new View.OnClickListener() { // The spike shop button
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Middleshop_Activity.this, Spike_ShopActivity.class);
                startActivity(intent); // Moving into spike shop activity
                overridePendingTransition(0, 0);
                finish();
            }
        });
        exit_to_main.setOnClickListener(new View.OnClickListener() { // Exit the page
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Middleshop_Activity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

    }
}