package com.example.savethepuppy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DifficultyActivity extends AppCompatActivity {
    Button easy_button, medium_button, hard_button, master_button;
    ImageView exit_to_main;
    SharedPreferences achievements; // Using the achieve data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        easy_button = findViewById(R.id.easy_button); // Adding the ids of all the uses in the diff_activity
        medium_button = findViewById(R.id.medium_button);
        hard_button = findViewById(R.id.hard_button);
        master_button = findViewById(R.id.master_button);
        exit_to_main = findViewById(R.id.exitdifficulty_button);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        SharedPreferences.Editor editor = getSharedPreferences("mode", MODE_PRIVATE).edit();
        achievements = getSharedPreferences("achievements", MODE_PRIVATE);

        // A custom toast

        Toast toast = new Toast(getApplicationContext());
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) View view = getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.new_toast));
        toast.setView(view);
        TextView toast_text = view.findViewById(R.id.text_toast);
        ImageView toast_icon = view.findViewById(R.id.icon_toast);
        toast_icon.setVisibility(View.GONE);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM | Gravity.START, 50, 50);

        // mode 0 = easy
        // mode 1 = medium
        // mode 2 = hard
        // mode 3 = master

        // Setting the image of the buttons according to the achieve data

        if (!achievements.getBoolean("medium_mode", false)) {
            medium_button.setBackgroundResource(R.drawable.color_button_lock);
        }
        if (!achievements.getBoolean("hard_mode", false)) {
            hard_button.setBackgroundResource(R.drawable.color_button_lock);
        }
        if (!achievements.getBoolean("master_mode", false)) {
            master_button.setBackgroundResource(R.drawable.color_button_lock);
        }

        easy_button.setOnClickListener(new View.OnClickListener() { // If u choose easy mode
            @Override
            public void onClick(View view) {
                editor.putInt("mode", 0); // Putting 0 (easy) value for later use in GamePlay class
                editor.apply();
                GamePlay StartGame = new GamePlay(DifficultyActivity.this); // Starting the game on easy mode
                setContentView(StartGame);
            }
        });

        medium_button.setOnClickListener(new View.OnClickListener() { // Starting the game on medium mode
            @Override
            public void onClick(View view) {
                if (achievements.getBoolean("medium_mode", false)) { // If u the boolean is true u have the mode
                    editor.putInt("mode", 1);  // Putting 1 (medium) value for later use in GamePlay class
                    editor.apply();
                    GamePlay StartGame = new GamePlay(DifficultyActivity.this); // Starting the game on medium mode
                    setContentView(StartGame);
                } else { // If u didn't unlock the this mode yet a message will appear
                    toast_text.setText("You need to get 4000 points in easy mode to unlock this mode");
                    toast.show();
                }
            }
        });

        hard_button.setOnClickListener(new View.OnClickListener() { // Starting the game on hard mode
            @Override
            public void onClick(View view) {
                if (achievements.getBoolean("hard_mode", false)) { // If u the boolean is true u have the mode
                    editor.putInt("mode", 2); // Putting 2 (hard) value for later use in GamePlay class
                    editor.apply();
                    GamePlay StartGame = new GamePlay(DifficultyActivity.this); // Starting the game on hard mode
                    setContentView(StartGame);
                } else { // If u didn't unlock the this mode yet a message will appear
                    toast_text.setText("You need to get 4000 points in medium mode to unlock this mode");
                    toast.show();
                }
            }
        });

        master_button.setOnClickListener(new View.OnClickListener() { // Starting the game on master mode
            @Override
            public void onClick(View view) {
                if (achievements.getBoolean("master_mode", false)) { // If u the boolean is true u have the mode
                    editor.putInt("mode", 3); // Putting 3 (master) value for later use in GamePlay class
                    editor.apply();
                    GamePlay StartGame = new GamePlay(DifficultyActivity.this); // Starting the game on master mode
                    setContentView(StartGame);
                } else { // If u didn't unlock the this mode yet a message will appear
                    toast_text.setText("You need to get 6000 points in hard mode to unlock this mode");
                    toast.show();
                }
            }
        });

        exit_to_main.setOnClickListener(new View.OnClickListener() { // Exit button
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficultyActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }
}