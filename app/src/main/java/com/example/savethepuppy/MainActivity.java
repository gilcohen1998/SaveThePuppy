package com.example.savethepuppy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

// The home page

public class MainActivity extends AppCompatActivity {
    Button Play, Shop, Info;
    ImageView donate, skin_storage;
    SharedPreferences coins;
    TextView main_coins, free_2_keys, free_6_keys, best_offer, less20, less40;
    Dialog dialog;
    ImageView exit_dialog;
    HorizontalScrollView scrollView;
    private Animation donation_effect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coins = getSharedPreferences("coins", MODE_PRIVATE);

        // Find the id of all the needed

        Play = findViewById(R.id.play_button);
        Shop = findViewById(R.id.shop_button);
        Info = findViewById(R.id.info_button);
        donate = findViewById(R.id.donate);
        skin_storage = findViewById(R.id.change_skin);
        main_coins = findViewById(R.id.main_coins);
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.donate_dialog);
        exit_dialog = dialog.findViewById(R.id.exit_dialog);
        scrollView = dialog.findViewById(R.id.horizontal_scroll_view);
        free_2_keys = dialog.findViewById(R.id.free_2_keys);
        free_6_keys = dialog.findViewById(R.id.free_6_keys);
        best_offer = dialog.findViewById(R.id.best_offer);
        less20 = dialog.findViewById(R.id.less20);
        less40 = dialog.findViewById(R.id.less40);
        donation_effect = AnimationUtils.loadAnimation(this, R.anim.donation_text_effect);

        dialog.getWindow().setDimAmount(0.0f);
        dialog.setCanceledOnTouchOutside(true);

        main_coins.setText("" + coins.getInt("coins", 0));

        Play.setOnClickListener(new View.OnClickListener() { // If u choose the play button options
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DifficultyActivity.class); // Creating new pass to diff activity
                startActivity(intent); // Moving to diff_activity
                overridePendingTransition(0, 0);
                finish(); // Finishing the current activity
            }
        });

        Shop.setOnClickListener(new View.OnClickListener() { // If u choose the shop button options
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Middleshop_Activity.class); // Creating new pass to middle shop activity
                startActivity(intent); // Moving to middle shop activity
                overridePendingTransition(0, 0);
                finish(); // Finishing the current activity
            }
        });

        skin_storage.setOnClickListener(new View.OnClickListener() { // If u choose the change skin button options
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Storage_activity.class); // Creating new pass to skin storage activity
                startActivity(intent); // Moving to skin storage activity
                overridePendingTransition(0, 0);
                finish(); // Finishing the current activity
            }
        });

        Info.setOnClickListener(new View.OnClickListener() { // If u choose the info button options
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class); // Creating new pass to info activity
                startActivity(intent); // Moving to info activity
                overridePendingTransition(0, 0);
                finish(); // Finishing the current activity
            }
        });

        // Opening the donate shop (dialog)

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "COMING SOON!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setGravity(Gravity.TOP | Gravity.CENTER);
                scrollView.post(new Runnable() {
                    public void run() {
                        scrollView.scrollTo(0, 0);
                    }
                });

                // Text anim

                free_2_keys.startAnimation(donation_effect);
                free_6_keys.startAnimation(donation_effect);
                best_offer.startAnimation(donation_effect);
                less20.startAnimation(donation_effect);
                less40.startAnimation(donation_effect);
                exit_dialog.setOnClickListener(new View.OnClickListener() { // Exit the dialog
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show(); // Showing the dialog
            }
        });
    }
}