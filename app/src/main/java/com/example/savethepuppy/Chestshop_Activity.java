package com.example.savethepuppy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

public class Chestshop_Activity extends AppCompatActivity {

    LottieAnimationView chest, effect; // Lottie anim class
    private boolean flag = false;
    private boolean flag2 = false;
    private Animation item_anim, get_gift_anim; // Anim class
    private ImageView chest_gift, exit_to_main;
    private TextView shop_keys;
    private final Random random = new Random(System.currentTimeMillis()); // A random new instance of random class
    SharedPreferences keys, skins, coins, spikes; // Saving data in the user's phone
    SharedPreferences.Editor editor_keys, editor_skins, editor_coins, editor_spikes; // Edit data in the user's phone


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chestshop);

        // Declare and find all the ids of all the vars

        exit_to_main = findViewById(R.id.exitchestshop_button);
        shop_keys = findViewById(R.id.shop_keys);
        chest_gift = findViewById(R.id.chest_gift);

        item_anim = AnimationUtils.loadAnimation(this, R.anim.item_anim);
        get_gift_anim = AnimationUtils.loadAnimation(this, R.anim.get_gift_anim);

        chest = findViewById(R.id.chest);
        effect = findViewById(R.id.effect);

        chest.setAnimation(R.raw.open_chest);
        effect.setAnimation(R.raw.effect);

        editor_coins = getSharedPreferences("coins", MODE_PRIVATE).edit();
        editor_skins = getSharedPreferences("skins", MODE_PRIVATE).edit();
        editor_keys = getSharedPreferences("keys", MODE_PRIVATE).edit();
        editor_spikes = getSharedPreferences("spikes", MODE_PRIVATE).edit();
        spikes = getSharedPreferences("spikes", MODE_PRIVATE);
        skins = getSharedPreferences("skins", MODE_PRIVATE);
        keys = getSharedPreferences("keys", MODE_PRIVATE);
        coins = getSharedPreferences("coins", MODE_PRIVATE);

        // Set a custom toast with a custom layout

        Toast toast = new Toast(getApplicationContext());
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) View view = getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.new_toast));
        toast.setView(view);
        TextView toast_text = view.findViewById(R.id.text_toast);
        ImageView toast_icon = view.findViewById(R.id.icon_toast);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM | Gravity.START, 50, 130);

        // Set the key's text as the number of the keys that the player have (invoke from the phone data)

        shop_keys.setText("" + keys.getInt("keys", 0));

        // The happening when a player click the chest in the shop

        chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (keys.getInt("keys", 0) > 0) { // If the player have more than zero keys

                    if (!flag) { // If flag is false (flag is a helper var that help using the chest without spamming it)
                        int gift = random.nextInt(9895); // Generate random int between 0 to 9895
                        editor_keys.putInt("keys", keys.getInt("keys", 0) - 1); // Reduce the key data by 1
                        editor_keys.apply(); // Apply the edit
                        shop_keys.setText("" + keys.getInt("keys", 0)); // Update the key's text

                        // The chest chance rolling system

                        if (gift <= 3000) {
                            chest_gift.setImageResource(R.drawable.bunny_chestshop);
                            if (skins.getInt("bunny", 0) == 0) { // If u don't have the skin
                                editor_skins.putInt("bunny", 1); // Putting 1 (the 1 var mean that u have the skin but not equipped) in the skin data
                                editor_skins.apply();
                            } else { // If u have the skin
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 20); // Giving the user 20 coins because he have the skin
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 20 coins"); // Show the player a custom toast
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 5000) {
                            chest_gift.setImageResource(R.drawable.cat_chestshop);
                            if (skins.getInt("cat", 0) == 0) {
                                editor_skins.putInt("cat", 1);
                                editor_skins.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 50);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 50 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 7000) {
                            chest_gift.setImageResource(R.drawable.dog_chestshop);
                            if (skins.getInt("dog", 0) == 0) {
                                editor_skins.putInt("dog", 1);
                                editor_skins.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 50);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 50 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 7500) {
                            chest_gift.setImageResource(R.drawable.fox_chestshop);
                            if (skins.getInt("fox", 0) == 0) {
                                editor_skins.putInt("fox", 1);
                                editor_skins.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 70);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 70 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 8000) {
                            chest_gift.setImageResource(R.drawable.tiger_chestshop);
                            if (skins.getInt("tiger", 0) == 0) {
                                editor_skins.putInt("tiger", 1);
                                editor_skins.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 200);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 200 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 8500) {
                            chest_gift.setImageResource(R.drawable.pig_chestshop);
                            if (skins.getInt("pig", 0) == 0) {
                                editor_skins.putInt("pig", 1);
                                editor_skins.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 250);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 250 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 8800) {
                            chest_gift.setImageResource(R.drawable.chicken_chestshop);
                            if (skins.getInt("chicken", 0) == 0) {
                                editor_skins.putInt("chicken", 1);
                                editor_skins.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 300);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 300 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 9100) {
                            chest_gift.setImageResource(R.drawable.bear_chestshop);
                            if (skins.getInt("bear", 0) == 0) {
                                editor_skins.putInt("bear", 1);
                                editor_skins.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 300);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 300 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 9200) {
                            chest_gift.setImageResource(R.drawable.monkey_chestshop);
                            if (skins.getInt("monkey", 0) == 0) {
                                editor_skins.putInt("monkey", 1);
                                editor_skins.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 1000);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 1000 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 9300) {
                            chest_gift.setImageResource(R.drawable.sheep_chestshop);
                            if (skins.getInt("sheep", 0) == 0) {
                                editor_skins.putInt("sheep", 1);
                                editor_skins.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 1000);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 1000 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 9400) {
                            chest_gift.setImageResource(R.drawable.cow_chestshop);
                            if (skins.getInt("cow", 0) == 0) {
                                editor_skins.putInt("cow", 1);
                                editor_skins.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 1000);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 1000 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 9500) {
                            chest_gift.setImageResource(R.drawable.panda_chestshop);
                            if (skins.getInt("panda", 0) == 0) {
                                editor_skins.putInt("panda", 1);
                                editor_skins.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 1000);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 1000 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 9550) {
                            chest_gift.setImageResource(R.drawable.lion_chestshop);
                            if (skins.getInt("lion", 0) == 0) {
                                editor_skins.putInt("lion", 1);
                                editor_skins.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 2000);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 2000 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 9580) {
                            chest_gift.setImageResource(R.drawable.owl_chestshop);
                            if (skins.getInt("owl", 0) == 0) {
                                editor_skins.putInt("owl", 1);
                                editor_skins.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 4000);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 4000 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 9600) {
                            chest_gift.setImageResource(R.drawable.giraffe_chestshop);
                            if (skins.getInt("giraffe", 0) == 0) {
                                editor_skins.putInt("giraffe", 1);
                                editor_skins.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 5000);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 5000 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 9620) {
                            chest_gift.setImageResource(R.drawable.elephant_chestshop);
                            if (skins.getInt("elephant", 0) == 0) {
                                editor_skins.putInt("elephant", 1);
                                editor_skins.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 5000);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 5000 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 9630) {
                            chest_gift.setImageResource(R.drawable.dragon_chestshop);
                            if (skins.getInt("dragon", 0) == 0) {
                                editor_skins.putInt("dragon", 1);
                                editor_skins.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 10000);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 10000 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 9680) {
                            chest_gift.setImageResource(R.drawable.coins1000_chestshop);
                            editor_coins.putInt("coins", coins.getInt("coins", 0) + 1000);
                            editor_coins.apply();
                        } else if (gift <= 9710) {
                            chest_gift.setImageResource(R.drawable.coins3000_chestshop);
                            editor_coins.putInt("coins", coins.getInt("coins", 0) + 3000);
                            editor_coins.apply();
                        } else if (gift <= 9730) {
                            chest_gift.setImageResource(R.drawable.coins6000_chestshop);
                            editor_coins.putInt("coins", coins.getInt("coins", 0) + 6000);
                            editor_coins.apply();
                        } else if (gift <= 9740) {
                            chest_gift.setImageResource(R.drawable.coins10000_chestshop);
                            editor_coins.putInt("coins", coins.getInt("coins", 0) + 10000);
                            editor_coins.apply();
                        } else if (gift <= 9760) {
                            chest_gift.setImageResource(R.drawable.keys2_chestshop);
                            editor_keys.putInt("keys", keys.getInt("keys", 0) + 2);
                            editor_keys.apply();
                            shop_keys.setText("" + keys.getInt("keys", 0));
                        } else if (gift <= 9770) {
                            chest_gift.setImageResource(R.drawable.keys3_chestshop);
                            editor_keys.putInt("keys", keys.getInt("keys", 0) + 3);
                            editor_keys.apply();
                            shop_keys.setText("" + keys.getInt("keys", 0));
                        } else if (gift <= 9830) {
                            chest_gift.setImageResource(R.drawable.ninja_sword_chestshop);
                            if (spikes.getInt("ninja_sword", 0) == 0) {
                                editor_spikes.putInt("ninja_sword", 1);
                                editor_spikes.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 500);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 500 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 9860) {
                            chest_gift.setImageResource(R.drawable.sword_chestshop);
                            if (spikes.getInt("sword", 0) == 0) {
                                editor_spikes.putInt("sword", 1);
                                editor_spikes.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 1000);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 1000 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 9880) {
                            chest_gift.setImageResource(R.drawable.steak_chestshop);
                            if (spikes.getInt("steak", 0) == 0) {
                                editor_spikes.putInt("steak", 1);
                                editor_spikes.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 4000);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 4000 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else if (gift <= 9890) {
                            chest_gift.setImageResource(R.drawable.bomb_chestshop);
                            if (spikes.getInt("bomb", 0) == 0) {
                                editor_spikes.putInt("bomb", 1);
                                editor_spikes.apply();
                            } else {
                                editor_coins.putInt("coins", coins.getInt("coins", 0) + 7000);
                                editor_coins.apply();
                                toast_text.setText("You already have this skin, you revived extra 7000 coins");
                                toast_icon.setImageResource(R.drawable.coins0);
                                toast.show();
                            }
                        } else {
                            chest_gift.setImageResource(R.drawable.keys4_chestshop);
                            editor_keys.putInt("keys", keys.getInt("keys", 0) + 4);
                            editor_keys.apply();
                            shop_keys.setText("" + keys.getInt("keys", 0));
                        }

                        chest.playAnimation(); // Starting the chest animation
                        flag = true; // Making sure there is no spam clicking
                        chest_gift.startAnimation(item_anim); // Starting the opening chest anim (skin reveal)
                        chest_gift.setVisibility(View.INVISIBLE);

                        new CountDownTimer(4500, 10) { // Counting few seconds (for using more anim after this one is finished)

                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                effect.setSpeed(0.7F);
                                effect.playAnimation(); // Using an other animation when the chest is still opening

                                effect.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (!flag2) {
                                            chest_gift.startAnimation(get_gift_anim);
                                            chest_gift.setVisibility(View.INVISIBLE);
                                            flag2 = true;
                                        } // Reset the chest opening (for opening a chest again)
                                        Intent intent = new Intent(Chestshop_Activity.this, Chestshop_Activity.class);
                                        startActivity(intent);
                                        overridePendingTransition(0, 0);
                                        finish();
                                    }
                                });
                            }

                        }.start();
                    }
                } else { // If u don't have any keys
                    toast_text.setText("You need one key to open the chest");
                    toast_icon.setImageResource(R.drawable.key);
                    toast.show();
                }
            }
        });


        exit_to_main.setOnClickListener(new View.OnClickListener() { // The exit button
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chestshop_Activity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

    }
}