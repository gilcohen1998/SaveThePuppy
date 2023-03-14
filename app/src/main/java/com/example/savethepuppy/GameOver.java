package com.example.savethepuppy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class GameOver extends AppCompatActivity {

    private InterstitialAd mInterstitialAd; // New ad instance

    TextView tvPoints, new_socre_text, tvHighest;
    SharedPreferences sharedPreferences, what_mode;
    ImageView ivNewHighest;
    int highest = 0; // Creating the best score var

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over); // Find all the ids of all the var needed
        tvPoints = findViewById(R.id.tvPoints);
        tvHighest = findViewById(R.id.tvHighest);
        ivNewHighest = findViewById(R.id.new_high_score);
        new_socre_text = findViewById(R.id.new_high_score_text);

        // Checking what mode did the user choose

        what_mode = getSharedPreferences("mode", MODE_PRIVATE); // Getting the mode (for saving the best score for each game mode)
        int Mode = what_mode.getInt("mode", 0);
        int points = getIntent().getExtras().getInt("points", 0); // Invoke the score for this game
        tvPoints.setText("" + points); // Setting the score's text
        sharedPreferences = getSharedPreferences("highest_score", MODE_PRIVATE); // Invoke the best score for each game mode
        if (Mode == 0) {
            highest = sharedPreferences.getInt("easy_highest", 0); // Getting the highest score of the user in easy mode
        } else if (Mode == 1) {
            highest = sharedPreferences.getInt("medium_highest", 0); // Getting the highest score of the user in medium mode
        } else if (Mode == 2) {
            highest = sharedPreferences.getInt("hard_highest", 0); // Getting the highest score of the user in hard mode
        } else if (Mode == 3) {
            highest = sharedPreferences.getInt("master_highest", 0); // Getting the highest score of the user in master mode
        }

        // If the user make new best score (the new score is better than his best score)

        if (points > highest) {
            ivNewHighest.setVisibility(View.VISIBLE);
            new_socre_text.setVisibility(View.VISIBLE);
            highest = points;
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // Searching what mode the user is played and then saving the new score in the right mode

            if (Mode == 0) {
                editor.putInt("easy_highest", highest);
                editor.apply();
            } else if (Mode == 1) {
                editor.putInt("medium_highest", highest);
                editor.apply();
            } else if (Mode == 2) {
                editor.putInt("hard_highest", highest);
                editor.apply();
            } else if (Mode == 3) {
                editor.putInt("master_highest", highest);
                editor.apply();
            }
        }
        tvHighest.setText("" + highest); // Setting the best score in the highest score's text

        MobileAds.initialize(this, new OnInitializationCompleteListener() { // Building the ad
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {}
        });
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-8705508237728537/5686889997", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });
    }

    public void restart(View view) { // Going back to diff activity

        if (mInterstitialAd != null) { // If we have an ad we will show it to the user
            mInterstitialAd.show(GameOver.this);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    // Set the ad reference to null so you don't show the ad a second time.
                    Intent intent = new Intent(GameOver.this, DifficultyActivity.class);
                    startActivity(intent);
                    finish();
                    mInterstitialAd = null;
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    // Called when ad fails to show.
                    Intent intent = new Intent(GameOver.this, DifficultyActivity.class);
                    startActivity(intent);
                    finish();
                    mInterstitialAd = null;
                }
            });
        } else {
                 Intent intent = new Intent(GameOver.this, DifficultyActivity.class);
                 startActivity(intent);
                 finish();
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }


    }

    public void exit(View view) { // Going back to the menu
        if (mInterstitialAd != null) { // If we have an ad we will show it to the user
            mInterstitialAd.show(GameOver.this);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    // Set the ad reference to null so you don't show the ad a second time.
                    Intent intent = new Intent(GameOver.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    mInterstitialAd = null;
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    // Called when ad fails to show.
                    Intent intent = new Intent(GameOver.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    mInterstitialAd = null;
                }
            });
        } else { // If the ad didn't load
            Intent intent = new Intent(GameOver.this, MainActivity.class);
            startActivity(intent);
            finish();
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }

    }

}